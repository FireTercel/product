package com.core.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.util.Assert;

import com.core.common.dao.IGenericBaseCommonDao;
import com.core.common.hibernate.qbc.CriteriaQuery;
import com.core.common.hibernate.qbc.HqlQuery;
import com.core.common.hibernate.qbc.PageList;
import com.core.common.model.common.DBTable;

/**
 * 类描述：DAO层泛型基类
 * @author 唐东宇
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("hiding")
public abstract class GenericBaseCommonDao<T,PK extends Serializable> implements IGenericBaseCommonDao {
	
	/**
	 * 初始化Log4j的一个实例
	 */
	private static final Logger logger = Logger
			.getLogger(GenericBaseCommonDao.class);

	/**
	 * 注入一个SessionFactory属性
	 * 并注入到父类（HibernateDaoSupport）
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		// 事务必须是开启的(Required)，否则获取不到
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * 获得该类的属性和类型
	 * @param entityName
	 * 注解的实体类
	 */
	@SuppressWarnings({ "unused", "rawtypes" })
	private <T> void getProperty(Class entityName){
		ClassMetadata cm=sessionFactory.getClassMetadata(entityName);
		String [] str=cm.getPropertyNames();
		for(int i=0;i<str.length;i++){
			String property=str[i];
			String type=cm.getPropertyType(property).getName();
			com.core.util.LogUtil.info(property+"---&gt"+type);
		}
	}
	
	@SuppressWarnings("rawtypes")
	public List<DBTable> getAllDbTableName() {
		return null;
	}

	/**
	 * 获取所有数据表的数量
	 * 
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public Integer getAllDbTableSize() {
		SessionFactory factory = getSession().getSessionFactory();
		Map<String, ClassMetadata> metaMap = factory.getAllClassMetadata();
		return metaMap.size();
	}

	/**
	 * 根据传入的实体持久化对象
	 */
	public <T> Serializable save(T entity) {
		try {
			Serializable id = getSession().save(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("保存实体成功," + entity.getClass().getName());
			}
			return id;
		} catch (RuntimeException e) {
			logger.error("保存实体异常", e);
			throw e;
		}
	}

	/**
	 * 批量保存数据
	 * 
	 * @param <T>
	 * @param entitys
	 *            要持久化的临时实体对象集合
	 */
	public <T> void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20个对象后才清理缓存，写入数据库
				getSession().flush();
				getSession().clear();
			}
		}
		// 最后清理一下----防止大于20小于40的不保存
		getSession().flush();
		getSession().clear();
	}

	/**
	 * 根据传入的实体添加或更新对象
	 * 
	 * @param <T>
	 * 
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity) {
		try {
			getSession().saveOrUpdate(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("添加或更新成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("添加或更新异常", e);
			throw e;
		}
	}
	
	/**
	 * 更新指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
		getSession().flush();

	}

	/**
	 * 根据主键更新实体
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void updateEntityById(Class entityName, Serializable id) {
		updateEntitie(get(entityName, id));
	}

	/**
	 * 根据Id获取对象。
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityName, Serializable id) {
		return (T) getSession().get(entityName, id);
	}

	/**
	 * 根据实体名字获取唯一记录
	 * 
	 * @param propertyName
	 * @param value
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (T) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).uniqueResult();
	}

	
	/**
	 * 按属性查找对象列表.
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).list();
	}

	

	/**
	 * 根据主键获取实体并加锁。
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @param lock
	 * @return
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> T getEntity(Class entityName, Serializable id) {
		T t = (T) getSession().get(entityName, id);
		if (t != null) {
			getSession().flush();
		}
		return t;
	}

	/**
	 * 根据传入的实体删除对象
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("删除成功," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("删除异常", e);
			throw e;
		}

	}
	
	/**
	 * 根据主键删除指定的实体
	 * 
	 * @param <T>
	 * @param pojo
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		delete(get(entityName, id));
		getSession().flush();

	}

	/**
	 * 删除全部的实体
	 * 
	 * @param <T>
	 * 
	 * @param entitys
	 */
	public <T> void deleteAllEntitie(Collection<T> entitys) {
		for (Object entity : entitys) {
			getSession().delete(entity);
			getSession().flush();
		}
	}
	
	
	/**
	 * 创建单一Criteria对象
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass) {
		Criteria criteria = getSession().createCriteria(entityClass);
		return criteria;
	}
	
	@SuppressWarnings("unchecked")
	public <T> List<T> loadAll(Class<T> entityClass) {
		Criteria criteria = createCriteria(entityClass);
		return criteria.list();
	}
	
	/**
	 * 创建Criteria对象带属性比较
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass,
			Criterion... criterions) {
		Criteria criteria = getSession().createCriteria(entityClass);
		for (Criterion c : criterions) {
			criteria.add(c);
		}
		return criteria;
	}
	
	/**
	 * 创建Criteria对象，有排序功能。
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param orderBy
	 * @param isAsc
	 * @param criterions
	 * @return
	 */
	private <T> Criteria createCriteria(Class<T> entityClass, boolean isAsc,
			Criterion... criterions) {
		Criteria criteria = createCriteria(entityClass);

		if (isAsc) {
			criteria.addOrder(Order.asc("ASC"));
		} else {
			criteria.addOrder(Order.desc("DESC"));
		}

		return criteria;
	}
	


	/**
	 * 通过hql 查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByQueryString(final String hql) {
		Query queryObject =getSession().createQuery(hql);
		List<T> list=queryObject.list();
		if(list.size()>0){
			getSession().flush();
		}
		return list;
	}

	/**
	 * 通过hql查询唯一对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> T singleResult(String hql) {
		T t=null;
		Query queryObject=getSession().createQuery(hql);
		List<T> list=queryObject.list();
		if(list.size()==1){
			t=list.get(0);
		}else if(list.size()>1){
			throw new RuntimeException("查询结果数:" + list.size() + "大于1");
		}
		
		return t;
	}

	/**
	 * 通过sql更新记录
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public int updateBySqlString(final String query) {
		Query querys = getSession().createSQLQuery(query);
		return querys.executeUpdate();
	}

	/**
	 * 通过sql查询语句查找对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findListbySql(String query) {
		Query querys=getSession().createSQLQuery(query);
		return querys.list();
	}

	/**
	 * 根据属性名和属性值查询. 有排序
	 * 
	 * @param <T>
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @param orderBy
	 * @param isAsc
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		Assert.hasText(propertyName);
		return createCriteria(entityClass, isAsc,
				Restrictions.eq(propertyName, value)).list();
	}

	/**
	 * 获取分页记录CriteriaQuery 老方法final int allCounts =
	 * oConvertUtils.getInt(criteria
	 * .setProjection(Projections.rowCount()).uniqueResult(), 0);
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(CriteriaQuery cq, boolean isOffset) {
		
		return null;
	}

	/**
	 * 根据CriteriaQuery获取List
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(CriteriaQuery cq, Boolean ispage) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageList(HqlQuery hqlQuery, boolean needParameter) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageListBySql(HqlQuery hqlQuery, boolean needParameter) {
		// TODO Auto-generated method stub
		return null;
	}


	/**
	 * 根据实体模版查找
	 * 
	 * @param entityName
	 * @param exampleEntity
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public List findByExample(final String entityName,final Object exampleEntity) {
		Assert.notNull(exampleEntity, "Example entity must not be null");
		Criteria executableCriteria = (entityName != null ? getSession()
				.createCriteria(entityName) : getSession().createCriteria(
				exampleEntity.getClass()));
		executableCriteria.add(Example.create(exampleEntity));
		return executableCriteria.list();
	}

	/**
	 * 通过hql 查询语句查找HashMap对象
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	public Map<Object, Object> getHashMapbyQuery(String query) {
		Query querys=getSession().createQuery(query);
		List list=querys.list();
		Map<Object, Object> map=new HashMap<Object, Object>();
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			Object[] tm = (Object[]) iterator.next();
			map.put(tm[0].toString(), tm[1].toString());
		}
		return map;
	}
	
	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Autowired
	@Qualifier("namedParameterJdbcTemplate")
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	/**
	 * 1、使用了springframework中的JDBC jar包，类JdbcTemplate。
	 */
	public Integer executeSql(String sql, List<Object> param) {
		return this.jdbcTemplate.update(sql, param);
	}

	/**
	 * 2、使用了springframework中的JDBC jar包，类JdbcTemplate。
	 */
	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql, param);
	}

	/**
	 * 3、使用了springframework中的JDBC jar包，类NamedParameterJdbcTemplate。
	 */
	public Integer executeSql(String sql, Map<String, Object> param) {
		return this.namedParameterJdbcTemplate.update(sql, param);
	}

	/**
	 * 
	 */
	public Object executeSqlReturnKey(final String sql, Map<String, Object> param) {
		Object keyValue = null;
		try{
			KeyHolder keyHolder = new GeneratedKeyHolder(); 
			SqlParameterSource sqlp  = new MapSqlParameterSource(param);
			this.namedParameterJdbcTemplate.update(sql,sqlp, keyHolder);
			keyValue = keyHolder.getKey().longValue();
		}catch (Exception e) {
			keyValue = null;
		}
		return keyValue;
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<String, Object> findOneForJdbc(String sql, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCountForJdbc(String sql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long getCountForJdbcParam(String sql, Object[] objs) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findHql(String hql, Object... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeHql(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByDetached(DetachedCriteria dc) {
		// TODO Auto-generated method stub
		return null;
	}

}
