package com.core.common.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.core.common.hibernate.qbc.CriteriaQuery;
import com.core.common.hibernate.qbc.HqlQuery;
import com.core.common.hibernate.qbc.PageList;
import com.core.common.model.common.DBTable;

/**
 * �ýӿڣ������ڲ������ݿ������ɾ���á���ȣ���������ж��ֲ�����ʽ���ɸ���ҵ������ѡ����巽����
 * ���滹�����в��ҷ�ҳ���ܣ�����ʹ��Ҫ�Լ������Կ���
 * 
 * @author �ƶ���
 *
 */
public interface IGenericBaseCommonDao {
	
	/**
	 * ��ȡ�������ݿ��
	 * 
	 * @return ����һ������List����ȡ�������ݿ������
	 */
	public List<DBTable> getAllDbTableName();
	
	
	/**
	 * ���ص���һ�����ͣ���ô��Ӧ�þ��Ǳ����Ŀ��
	 * @return
	 */
	public Integer getAllDbTableSize();
	
	/**
	 * ����ʵ�������ݣ��������ݿ����������
	 * @param entity
	 * @return
	 */
	public <T> Serializable save(T entity);
	
	/**
	 * ӡ���У��������Ҳ�����ݿ��������������ǲ��ᱨ����
	 * @param entitys
	 */
	public <T> void batchSave(List<T> entitys);
	
	/**
	 * ����˼�壬�������ӡ����߸��µ���˼��
	 * @param entity
	 */
	public <T> void saveOrUpdate(T entity);
	

	
	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> entityName, Serializable id);
	
	/**
	 * ����ʵ�����ֻ�ȡΨһ��¼
	 * 
	 * @param entityClass
	 * @param propertyName
	 * @param value
	 * @return
	 */
	public <T> T findUniqueByProperty(Class<T> entityClass,
			String propertyName, Object value);
	
	
	/**
	 * �����Բ��Ҷ����б�.
	 */
	public <T> List<T> findByProperty(Class<T> entityClass,
			String propertyName, Object value);
	
	/**
	 * ����ȫ��ʵ��
	 * ���ݲ�������ʵ�弯�ϣ���ȡList��
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);
	
	
	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);
	
	
	public <T> void deleteEntityById(Class entityName, Serializable id);
	
	/**
	 * ɾ��ʵ��
	 * @param <T>
	 * @param entitie
	 */
	public <T> void delete(T entitie);
	
	
	/**
	 * ɾ��ʵ�弯��
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteAllEntitie(Collection<T> entities);
	
	/**
	 * ����ָ����ʵ��
	 * 
	 * @param <T>
	 * @param pojo
	 */
	public <T> void updateEntitie(T pojo);
	
	
	public <T> void updateEntityById(Class entityName, Serializable id);
	
	
	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql);
	
	
	/**
	 * ͨ��hql��ѯΨһ����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> T singleResult(String hql);
	
	
	/**
	 * ����sql����
	 * 
	 * @param query
	 * @return
	 */
	public int updateBySqlString(String sql);
	
	
	/**
	 * ����sql����List
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findListbySql(String query);
	
	/**
	 * ͨ�����Գƻ�ȡʵ�������
	 * 
	 * @param <T>
	 * @param clas
	 * @return
	 */
	public <T> List<T> findByPropertyisOrder(Class<T> entityClass,
			String propertyName, Object value, boolean isAsc);
	
	/**
	 * 
	 * cq��ʽ��ҳ
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final CriteriaQuery cq, final boolean isOffset);
	
	
	/**
	 * ͨ��cq��ȡȫ��ʵ��
	 * 
	 * @param <T>
	 * @param cq
	 * @return
	 */
	public <T> List<T> getListByCriteriaQuery(final CriteriaQuery cq,
			Boolean ispage);
	
	/**
	 * 
	 * hqlQuery��ʽ��ҳ
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageList(final HqlQuery hqlQuery,
			final boolean needParameter);
	
	
	/**
	 * 
	 * sqlQuery��ʽ��ҳ
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	public PageList getPageListBySql(final HqlQuery hqlQuery,
			final boolean needParameter);
	
	public Session getSession();

	public List findByExample(final String entityName,
			final Object exampleEntity);
	
	/**
	 * ͨ��hql ��ѯ������HashMap����
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public Map<Object, Object> getHashMapbyQuery(String query);
	
	/**
	 * ����jquery datatablesģ��
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	/*public DataTableReturn getDataTableReturn(final CriteriaQuery cq,
			final boolean isOffset);*/

	/**
	 * ����easyui datagridģ��
	 * 
	 * @param cq
	 * @param isOffset
	 * @return
	 */
	/*public DataGridReturn getDataGridReturn(final CriteriaQuery cq,
			final boolean isOffset);*/
	
	/**
	 * ִ��SQL
	 */
	public Integer executeSql(String sql, List<Object> param);

	/**
	 * ִ��SQL
	 */
	public Integer executeSql(String sql, Object... param);
	
	
	/**
	 * ִ��SQL ʹ��:nameռλ��
	 */
	public Integer executeSql(String sql, Map<String, Object> param);
	/**
	 * ִ��SQL ʹ��:nameռλ��,�����ز��������ֵ
	 */
	public Object executeSqlReturnKey(String sql, Map<String, Object> param);
	/**
	 * ͨ��JDBC���Ҷ��󼯺� ʹ��ָ���ļ�����׼�������ݷ�������
	 */
	public List<Map<String, Object>> findForJdbc(String sql, Object... objs);

	/**
	 * ͨ��JDBC���Ҷ��󼯺� ʹ��ָ���ļ�����׼�������ݷ�������
	 */
	public Map<String, Object> findOneForJdbc(String sql, Object... objs);

	/**
	 * ͨ��JDBC���Ҷ��󼯺�,����ҳ ʹ��ָ���ļ�����׼�������ݲ���ҳ��������
	 */
	public List<Map<String, Object>> findForJdbc(String sql, int page, int rows);

	/**
	 * ͨ��JDBC���Ҷ��󼯺�,����ҳ ʹ��ָ���ļ�����׼�������ݲ���ҳ��������
	 */
	public <T> List<T> findObjForJdbc(String sql, int page, int rows,
			Class<T> clazz);

	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������-����Ԥ����ʽ
	 * 
	 * @param criteria
	 * @param firstResult
	 * @param maxResults
	 * @return
	 * @throws DataAccessException
	 */
	public List<Map<String, Object>> findForJdbcParam(String sql, int page,
			int rows, Object... objs);

	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC
	 */
	public Long getCountForJdbc(String sql);

	/**
	 * ʹ��ָ���ļ�����׼�������ݲ���ҳ��������For JDBC-����Ԥ����ʽ
	 * 
	 */
	public Long getCountForJdbcParam(String sql, Object[] objs);

	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findHql(String hql, Object... param);

	/**
	 * ִ��HQL����������
	 * 
	 * @param hql
	 * @return
	 */
	public Integer executeHql(String hql);

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult);

	public <T> List<T> findByDetached(DetachedCriteria dc);
	

}
