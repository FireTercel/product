package com.core.common.dao;

import javax.servlet.http.HttpServletResponse;

import com.core.common.model.common.UploadFile;

public interface ICommonDao extends IGenericBaseCommonDao {
	
	/**
	 * 文件上传
	 * @param uploadFile
	 * @return
	 */
	public <T> T UploadFile(UploadFile uploadFile );
	
	/**
	 * 文件上传或预览
	 * @param uploadFile
	 * @return
	 */
	public HttpServletResponse viewOrDownloadFile(UploadFile uploadFile);
	
	

}
