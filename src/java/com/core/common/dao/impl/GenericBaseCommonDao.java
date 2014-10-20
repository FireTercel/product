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
 * ��������DAO�㷺�ͻ���
 * @author �ƶ���
 *
 * @param <T>
 * @param <PK>
 */
@SuppressWarnings("hiding")
public abstract class GenericBaseCommonDao<T,PK extends Serializable> implements IGenericBaseCommonDao {
	
	/**
	 * ��ʼ��Log4j��һ��ʵ��
	 */
	private static final Logger logger = Logger
			.getLogger(GenericBaseCommonDao.class);

	/**
	 * ע��һ��SessionFactory����
	 * ��ע�뵽���ࣨHibernateDaoSupport��
	 */
	@Autowired
	@Qualifier("sessionFactory")
	private SessionFactory sessionFactory;
	
	public Session getSession(){
		// ��������ǿ�����(Required)�������ȡ����
		return sessionFactory.getCurrentSession();
	}
	
	/**
	 * ��ø�������Ժ�����
	 * @param entityName
	 * ע���ʵ����
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
	 * ��ȡ�������ݱ������
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
	 * ���ݴ����ʵ��־û�����
	 */
	public <T> Serializable save(T entity) {
		try {
			Serializable id = getSession().save(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("����ʵ��ɹ�," + entity.getClass().getName());
			}
			return id;
		} catch (RuntimeException e) {
			logger.error("����ʵ���쳣", e);
			throw e;
		}
	}

	/**
	 * ������������
	 * 
	 * @param <T>
	 * @param entitys
	 *            Ҫ�־û�����ʱʵ����󼯺�
	 */
	public <T> void batchSave(List<T> entitys) {
		for (int i = 0; i < entitys.size(); i++) {
			getSession().save(entitys.get(i));
			if (i % 20 == 0) {
				// 20�������������棬д�����ݿ�
				getSession().flush();
				getSession().clear();
			}
		}
		// �������һ��----��ֹ����20С��40�Ĳ�����
		getSession().flush();
		getSession().clear();
	}

	/**
	 * ���ݴ����ʵ����ӻ���¶���
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
				logger.debug("��ӻ���³ɹ�," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("��ӻ�����쳣", e);
			throw e;
		}
	}
	
	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
		getSession().flush();

	}

	/**
	 * ������������ʵ��
	 */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public <T> void updateEntityById(Class entityName, Serializable id) {
		updateEntitie(get(entityName, id));
	}

	/**
	 * ����Id��ȡ����
	 */
	@SuppressWarnings("unchecked")
	public <T> T get(Class<T> entityName, Serializable id) {
		return (T) getSession().get(entityName, id);
	}

	/**
	 * ����ʵ�����ֻ�ȡΨһ��¼
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
	 * �����Բ��Ҷ����б�.
	 */
	@SuppressWarnings("unchecked")
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		Assert.hasText(propertyName);
		return (List<T>) createCriteria(entityClass,
				Restrictions.eq(propertyName, value)).list();
	}

	

	/**
	 * ����������ȡʵ�岢������
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
	 * ���ݴ����ʵ��ɾ������
	 */
	public <T> void delete(T entity) {
		try {
			getSession().delete(entity);
			getSession().flush();
			if (logger.isDebugEnabled()) {
				logger.debug("ɾ���ɹ�," + entity.getClass().getName());
			}
		} catch (RuntimeException e) {
			logger.error("ɾ���쳣", e);
			throw e;
		}

	}
	
	/**
	 * ��������ɾ��ָ����ʵ��
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
	 * ɾ��ȫ����ʵ��
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
	 * ������һCriteria����
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
	 * ����Criteria��������ԱȽ�
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
	 * ����Criteria�����������ܡ�
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
	 * ͨ��hql ��ѯ�����Ҷ���
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
	 * ͨ��hql��ѯΨһ����
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
			throw new RuntimeException("��ѯ�����:" + list.size() + "����1");
		}
		
		return t;
	}

	/**
	 * ͨ��sql���¼�¼
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
	 * ͨ��sql��ѯ�����Ҷ���
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
	 * ����������������ֵ��ѯ. ������
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
	 * ��ȡ��ҳ��¼CriteriaQuery �Ϸ���final int allCounts =
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
	 * ����CriteriaQuery��ȡList
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
	 * ����ʵ��ģ�����
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
	 * ͨ��hql ��ѯ������HashMap����
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
	 * 1��ʹ����springframework�е�JDBC jar������JdbcTemplate��
	 */
	public Integer executeSql(String sql, List<Object> param) {
		return this.jdbcTemplate.update(sql, param);
	}

	/**
	 * 2��ʹ����springframework�е�JDBC jar������JdbcTemplate��
	 */
	public Integer executeSql(String sql, Object... param) {
		return this.jdbcTemplate.update(sql, param);
	}

	/**
	 * 3��ʹ����springframework�е�JDBC jar������NamedParameterJdbcTemplate��
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
