package com.wzxy.base.utils;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * resuest 辅助类
 * 
 * @author caibin
 *
 */
public class ReqUtils {
	public static Logger logger = LoggerFactory.getLogger(ReqUtils.class);
	
	public static String getParam(HttpServletRequest request,String paramName,String defaultValue){
		String value = request.getParameter(paramName);
		if (StringUtils.isNotBlank(value)){
			return value;
		}
		
		return defaultValue; 
	}
	/**
	 * 读取request参数值
	 * @param request 
	 * @param paramName 
	 * @param defaultValue 
	 * @return
	 */ 
	public static int getParamToInt(HttpServletRequest request, String paramName,int defaultValue){
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		
		try{	  
			int r = Integer.valueOf(value).intValue();
			return r;
		}catch(Exception ex){}
		return defaultValue;
	}
	
	/**
	 * 读取request参数值
	 * @param request 
	 * @param paramName 
	 * @param defaultValue 
	 * @return
	 */ 
	public static Integer getParamToInteger(HttpServletRequest request, String paramName,Integer defaultValue){
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		
		try{	  
			Integer r = Integer.valueOf(value);
			return r;
		}catch(Exception ex){}
		return defaultValue;
	}
	
	
	/**
	 * 读取request参数值
	 * @param request 
	 * @param paramName 
	 * @param defaultValue 
	 * @return
	 */ 
	public static double getParamToDouble(HttpServletRequest request,String paramName,double defaultValue){
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		try{	  
			double r = Double.parseDouble(value);
			return r;
		}catch(Exception ex){}
		return defaultValue;
	}
	
	/**
	 * 读取request参数值
	 * @param request 
	 * @param paramName 
	 * @param defaultValue 
	 * @return
	 */ 
	public static Date getParamToDate(HttpServletRequest request, String paramName, Date defaultValue){
		String value = request.getParameter(paramName);
		if (StringUtils.isBlank(value)) {
			return defaultValue;
		}
		
		try{
			if(StringUtils.isNumeric(value)){
				long dateLong = Long.valueOf(value);
				return new Date(dateLong);
			}
			DateFormat df = DateFormat.getDateInstance();
			return df.parse(value);
		}catch(Exception ex){}
		return defaultValue;
	}
	
	/**
	 * 获取用户ip地址
	 * @param request
	 * @return
	 */
	public static String getUserIp(HttpServletRequest request){
		if (request == null){
			return "";
		}
		
		String ip = request.getHeader("x-forwarded-for");//nginx代理过来的头 
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {		
			ip = request.getHeader("X-real-ip");
		}		
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		if (!StringUtils.isBlank(ip) && ip.indexOf(",") > 0){
			ip = ip.substring(0,ip.indexOf(",")).trim();
		}
		return ip;
	}
}
