package com.core.extend.datasource;

import java.util.Map;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.jdbc.datasource.lookup.DataSourceLookup;

/**
 *������DynamicDataSource.java
 *���ܣ���̬����Դ��
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

	/* 
	 * �÷�������Ҫ��д  ������Ϊ�˸������ݿ��ʾ��ȡ�õ�ǰ�����ݿ�
	 */
	
	protected Object determineCurrentLookupKey() {
		DataSourceType dataSourceType= DataSourceContextHolder.getDataSourceType();
		return dataSourceType;
	}

	
	public void setDataSourceLookup(DataSourceLookup dataSourceLookup) {
		super.setDataSourceLookup(dataSourceLookup);
	}

	
	public void setDefaultTargetDataSource(Object defaultTargetDataSource) {
		super.setDefaultTargetDataSource(defaultTargetDataSource);
	}

	
	public void setTargetDataSources(Map targetDataSources) {
		super.setTargetDataSources(targetDataSources);
	}

}

