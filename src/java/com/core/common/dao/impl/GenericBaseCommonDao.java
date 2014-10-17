package com.core.common.dao.impl;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.metadata.ClassMetadata;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.core.common.dao.IGenericBaseCommonDao;
import com.core.common.hibernate.qbc.CriteriaQuery;
import com.core.common.hibernate.qbc.HqlQuery;
import com.core.common.hibernate.qbc.PageList;
import com.core.common.model.common.DBTable;
import com.sun.corba.se.impl.orbutil.GetPropertyAction;

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
	private <T> void getProperty(Class entityName){
		ClassMetadata cm=sessionFactory.getClassMetadata(entityName);
		String [] str=cm.getPropertyNames();
		for(int i=0;i<str.length;i++){
			String property=str[i];
			String type=cm.getPropertyType(property).getName();
			com.core.util.LogUtil.info(property+"---&gt"+type);
		}
	}
	
	@Override
	public List<DBTable> getAllDbTableName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer getAllDbTableSize() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> Serializable save(T entity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void batchSave(List<T> entitys) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void saveOrUpdate(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> T get(Class<T> entityName, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> loadAll(Class<T> entityClass) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T getEntity(Class entityName, Serializable id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> void deleteEntityById(Class entityName, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void delete(T entitie) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void deleteAllEntitie(Collection<T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void updateEntitie(T pojo) {
		getSession().update(pojo);
		getSession().flush();

	}

	@Override
	public <T> void updateEntityById(Class entityName, Serializable id) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> List<T> findByQueryString(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> T singleResult(String hql) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int updateBySqlString(String sql) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public <T> List<T> findListbySql(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public PageList getPageList(CriteriaQuery cq, boolean isOffset) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
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


	@Override
	public List findByExample(String entityName, Object exampleEntity) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Map<Object, Object> getHashMapbyQuery(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, List<Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, Object... param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Integer executeSql(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object executeSqlReturnKey(String sql, Map<String, Object> param) {
		// TODO Auto-generated method stub
		return null;
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
