package com.core.common.hibernate.qbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 *��������CriteriaQuery���Ƕ�hibernate QBC��ѯ�����ķ�װ����Ҫ�Ĳ����ǵ�ǰ������ʵ���ࡣ
 *�Ŵ���
 *@date�� ���ڣ�2012-12-7 ʱ�䣺����10:22:15
 *@version 1.0
 */
public class CriteriaQuery implements Serializable {
	
	public CriteriaQuery(){
		
	}
	
	private static final long serialVersionUID=1L;
	private int curPage=1;//��ǰҳ
	private int pageSize=10;//Ĭ��һҳ����
	private String myAction;//�����action�ĵ�ַ
	private String myForm;//form������
	private CriterionList criterionList=new CriterionList();//�Զ����ѯ��������
	private CriterionList jqcriterionList=new CriterionList();//jquery datatable�ؼ����ɲ�ѯ��������
	private int isUseimage=0;//��ҳ��������ʽ
	private DetachedCriteria detachedCriteria;
	private static Map<String, Object> map;
	private static Map<String, Object> ordermap;//�����ֶ�
	private boolean flag = true;// ��ͬһ�ֶν��еڶ�����������ѯʱֵ����FASLE��������������ѯ����
	private String field="";//��ѯ��Ҫ��ʾ���ֶ�
	private Class entityClass;//POJO
	private List results;// �����
	private int total;
	private List<String> alias = new ArrayList<String>();//���洴����aliasName ��ֹ�ظ�����
	
	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
	
	

}
