package com.core.util;

import java.util.ResourceBundle;

/**
 * ��Ŀ����������
 * @author �ƶ���
 *
 */
public class ResourceUtil {
	
	private static final ResourceBundle bundle=java.util.ResourceBundle.getBundle("sysConfig");
	
	public static final String getSessionattachmenttitle(String sessionName){
		return bundle.getString(sessionName);
	}
	
	
}
