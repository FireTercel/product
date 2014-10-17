package com.core.common.dao;

import javax.servlet.http.HttpServletResponse;

import com.core.common.model.common.UploadFile;

public interface ICommonDao extends IGenericBaseCommonDao {
	
	/**
	 * �ļ��ϴ�
	 * @param uploadFile
	 * @return
	 */
	public <T> T UploadFile(UploadFile uploadFile );
	
	/**
	 * �ļ��ϴ���Ԥ��
	 * @param uploadFile
	 * @return
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);
	
	

}
