package com.clothes.util;

import java.text.SimpleDateFormat;
import java.util.Date;


public class SystemTimeUtil {
	public static String getSystemTime(){
		String systemTime=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());
		return systemTime;
	}
	
	public static String getSystemTimePic(){
		String systemTime=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return systemTime;
	}

}
