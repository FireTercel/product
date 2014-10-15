package com.core.common.hibernate.qbc;

import java.util.List;


@SuppressWarnings("unchecked")
public class PageList {
	private int curPageNO;
	private int offset;
	private String toolBar;//��ҳ������
	private int count;
	private List resultList = null;//�����
	public PageList() {
		
		
	}
	
	/**
	 * ��ʹ�÷�ҳ��ǩ�ĳ�ʼ�����췽��
	 * @param resultList
	 * @param toolBar
	 * @param offset
	 * @param curPageNO
	 * @param count
	 */
	public PageList(List resultList, String toolBar, int offset, int curPageNO, int count) {
		this.curPageNO = curPageNO;
		this.offset = offset;
		this.toolBar = toolBar;
		this.resultList = resultList;
		this.count = count;
	}
	
	/**
	 * ʹ�÷�ҳ��ǩ�ĳ�ʼ�����췽��
	 * @param resultList
	 * @param toolBar
	 * @param offset
	 * @param curPageNO
	 * @param count
	 */
	public PageList(CriteriaQuery cq,List resultList, int offset, int curPageNO, int count) {
		this.curPageNO = curPageNO;
		this.offset = offset;
		this.resultList = resultList;
		this.count = count;
	}
	public PageList(HqlQuery cq,List resultList, int offset, int curPageNO, int count) {
		this.curPageNO = curPageNO;
		this.offset = offset;
		this.resultList = resultList;
		this.count = count;
	}
	public <T> List<T> getResultList() {
		return resultList;
	}

	public void setResultList(List resultList) {
		this.resultList = resultList;
	}

	public String getToolBar() {
		return toolBar;
	}

	public int getCount() {
		return count;
	}

	public int getCurPageNO() {
		return curPageNO;
	}

	public void setCurPageNO(int curPageNO) {
		this.curPageNO = curPageNO;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}
	
	

}
