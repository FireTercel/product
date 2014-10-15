package com.core.util;

import java.util.ResourceBundle;

/**
 * 项目参数工具类
 * @author 唐东宇
 *
 */
public class ResourceUtil {
	
	private static final ResourceBundle bundle=java.util.ResourceBundle.getBundle("sysConfig");
	
	public static final String getSessionattachmenttitle(String sessionName){
		return bundle.getString(sessionName);
	}
	
	
}
