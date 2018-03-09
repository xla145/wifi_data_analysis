package com.wzxy.base.constant;


import com.wzxy.base.utils.ConfigUtil;
import com.wzxy.base.utils.DateUtil;

/**
 * 
 * 常量类
 * 
 * @author caibin
 *
 */
public class BaseConstant {

	public static boolean isDev = false;
	static{
		isDev = ConfigUtil.getBooleanValue("is.dev");
	}
	
	/**
	 * 系统用户登录session标识
	 */
	public static final String SYS_UID = "uid";
	public static final String SYS_USER = "sys_user";

	/**
	 * 客户登录session标识
	 */
	public static final String SYS_CID = "cid";
	public static final String SYS_CUSTOMERS = "sys_customers";
	
	
	/**
	 * DELFLAG 值全局设置
	 */
	public static final Integer DELFLAG_UNAVAILABLE = 0 ; //不可用
	
	public static final Integer DELFLAG_AVAILABLE = 1 ; //可用
	
	public static String genOrderId(String bussinessType){
		return bussinessType + "-" + DateUtil.now() + getRandomNum(100, 1000);
	}
	
	public static Integer getRandomNum(Integer start,Integer end){
		return (int)(Math.random()*(end-start)+start);
	}
	public static void main(String[] args) {
		System.out.println(genOrderId("IOT"));
	}
}
