package com.core.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

/**
 * @ClassName:ContextHolderUtils
 * @Description: TODO(CONTEXT)
 * @author Administrator
 *
 */
public class ContextHolderUtils {
	
	public static HttpServletRequest getRequest(){
		HttpServletRequest request=((ServletRequestAttributes)RequestContextHolder.getRequestAttributes()).getRequest();
		return request;
	}
	
	public static HttpSession getSession(){
		HttpSession session=getRequest().getSession();
		return session;
	}

}
