package com.wzxy.base.utils;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;

/**
 * 获取 resources.properties 参数 
 *
 * @author caixb
 */
public class ConfigUtil {
	public static final String CONFIG_FILE_NAME = "resources.properties";
	public static Properties props = new Properties();

	static {
		InputStream is = null;
		try {
			is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
			props.load(is);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			closeInputStream(is);
		}
	}
	
	/**
	 * 读取配置文件
	 */
	public static void reload(){
		InputStream is = null;
	    try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE_NAME);
            props.load(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
			closeInputStream(is);
		}
	}
	
	/**
	 * 获得配置的key
	 * @param key key-name
	 * @return
	 */
	public static String getValue(String key) {
		return props.getProperty(key);
	}
	
	/**
	 * 获得配置的key
	 * 
	 * @param key	key
	 * @param defaultVal  默认值
	 * @return
	 */
	public static String getValue(String key, String defaultVal) {
		String val = props.getProperty(key);
		if(StringUtils.isBlank(val)){
			return defaultVal;
		}
		return val;
	}
	
	/**
	 * 获得配置的key
	 * @param key key-name
	 * @return
	 */
	public static boolean getBooleanValue(String key) {
		boolean b = false;
		try {
			String str = props.getProperty(key);
			b = Boolean.valueOf(str);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return b;
	}
	
	/**
	 * 读取所有配置
	 * @return
	 */
	public static Properties getProps(){
	    return props;
	}
	
	/**
	 * 设置配置的key
	 * @param key key-name
	 * @param value key-value 
	 */
	public static void updateProperties(String key, String value) {
		props.setProperty(key, value);
	}

	/**
	 * 关闭io流
	 * @param is
	 * @throws IOException
	 */
	public static void closeInputStream(InputStream is) {
		if (is != null){
			try {
				is.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
