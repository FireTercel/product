package com.core.common.hibernate.qbc;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.hibernate.criterion.DetachedCriteria;

/**
 * 
 *类描述：CriteriaQuery类是对hibernate QBC查询方法的封装，需要的参数是当前操作的实体类。
 *张代浩
 *@date： 日期：2012-12-7 时间：上午10:22:15
 *@version 1.0
 */
public class CriteriaQuery implements Serializable {
	
	public CriteriaQuery(){
		
	}
	
	private static final long serialVersionUID=1L;
	private int curPage=1;//当前页
	private int pageSize=10;//默认一页条数
	private String myAction;//请求的action的地址
	private String myForm;//form的名字
	private CriterionList criterionList=new CriterionList();//自定义查询条件集合
	private CriterionList jqcriterionList=new CriterionList();//jquery datatable控件生成查询条件集合
	private int isUseimage=0;//翻页工具条样式
	private DetachedCriteria detachedCriteria;
	private static Map<String, Object> map;
	private static Map<String, Object> ordermap;//排序字段
	private boolean flag = true;// 对同一字段进行第二次重命名查询时值设置FASLE不保存重命名查询条件
	private String field="";//查询需要显示的字段
	private Class entityClass;//POJO
	private List results;// 结果集
	private int total;
	private List<String> alias = new ArrayList<String>();//保存创建的aliasName 防止重复创建
	
	public List getResults() {
		return results;
	}

	public void setResults(List results) {
		this.results = results;
	}
	
	
	

}
