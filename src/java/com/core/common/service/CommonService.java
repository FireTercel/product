package com.core.common.service;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;

import com.core.common.hibernate.qbc.CriteriaQuery;
import com.core.common.hibernate.qbc.HqlQuery;
import com.core.common.hibernate.qbc.PageList;
import com.core.common.model.common.DBTable;
import com.core.common.model.common.UploadFile;

public interface CommonService {
	
	/**
	 * ��ȡ�������ݿ��
	 * 
	 * @return
	 */
	public List<DBTable> getAllDbTableName();

	public Integer getAllDbTableSize();

	public <T> Serializable save(T entity);

	public <T> void saveOrUpdate(T entity);

	public <T> void delete(T entity);

	public <T> void batchSave(List<T> entitys);
	
	
	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T get(Class<T> class1, Serializable id);

	/**
	 * ����ʵ�����ƺ�������ȡʵ��
	 * 
	 * @param <T>
	 * @param entityName
	 * @param id
	 * @return
	 */
	public <T> T getEntity(Class entityName, Serializable id);

	/**
	 * ����ʵ�����ƺ��ֶ����ƺ��ֶ�ֵ��ȡΨһ��¼
	 * 
	 * @param <T>
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
	 * 
	 * @param <T>
	 * @param entityClass
	 * @return
	 */
	public <T> List<T> loadAll(final Class<T> entityClass);

	/**
	 * ɾ��ʵ������ɾ��
	 * 
	 * @param <T>
	 * @param entities
	 */
	public <T> void deleteEntityById(Class entityName, Serializable id);

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

	/**
	 * ͨ��hql ��ѯ�����Ҷ���
	 * 
	 * @param <T>
	 * @param query
	 * @return
	 */
	public <T> List<T> findByQueryString(String hql);

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

	public <T> List<T> getList(Class clas);

	public <T> T singleResult(String hql);

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
	 * ����DataTableReturnģ��
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
			final boolean isToEntity);

	public Session getSession();

	public List findByExample(final String entityName,
			final Object exampleEntity);

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
	 * �ļ��ϴ�
	 * 
	 * @param request
	 */
	public <T> T uploadFile(UploadFile uploadFile);

	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);

	/**
	 * ����XML�ļ�
	 * 
	 * @param fileName
	 *            XMLȫ·��
	 */
	/*public HttpServletResponse createXml(ImportFile importFile);*/

	/**
	 * ����XML�ļ�
	 * 
	 * @param fileName
	 *            XMLȫ·��
	 */
	public void parserXml(String fileName);

	/*public List<ComboTree> comTree(List<TSDepart> all, ComboTree comboTree);*/

	/**
	 * ����ģ������JSON
	 * 
	 * @param all
	 *            ȫ������
	 * @param in
	 *            ��ӵ�еĶ���
	 * @param comboBox
	 *            ģ��
	 * @return
	 */
	/*public List<ComboTree> ComboTree(List all, ComboTreeModel comboTreeModel,
			List in);*/

	/**
	 * �����������ݱ�
	 * 
	 * @param all
	 * @param treeGridModel
	 * @return
	 */
	/*public List<TreeGrid> treegrid(List all, TreeGridModel treeGridModel);*/

	/**
	 * ��ȡ�Զ�����б�
	 * 
	 * @param <T>
	 * @return
	 */
	/*public <T> List<T> getAutoList(Autocomplete autocomplete);*/

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
	 * ִ��SQL ʹ��:nameռλ��,������ִ�к������ֵ
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

	public <T> List<T> pageList(DetachedCriteria dc, int firstResult,
			int maxResult);

	public <T> List<T> findByDetached(DetachedCriteria dc);

}
