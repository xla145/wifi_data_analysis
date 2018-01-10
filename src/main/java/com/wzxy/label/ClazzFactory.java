package com.wzxy.label;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 建造操作类实例工厂
 * 
 * @author caibin
 *
 */
public class ClazzFactory {
	static Logger logger = LoggerFactory.getLogger(ClazzFactory.class);
	
	static Map<String, Class<ModuleData>> instanceMap1 = new HashMap<String, Class<ModuleData>>();
	static Map<String, Class<ModuleData>> instanceMap2 = new HashMap<String, Class<ModuleData>>();
	
	public static ModuleData getOperator(String pack, String model) throws InstantiationException, IllegalAccessException{
		try {
			Class<ModuleData> clazz = getClassInfo(pack, model);
			return (ModuleData) clazz.forName(clazz.getName()).newInstance();
		} catch (Exception e) {
			//logger.error("err",e);
		}
		
		logger.error("can't find model :" + model);
		return null;
	}
	
	@SuppressWarnings("unchecked")
	public static ModuleData getOperator(String clazz) throws InstantiationException, IllegalAccessException{
		if(StringUtils.isBlank(clazz)){
			logger.error("class无效, class:" + clazz);
			return null;
		}
		try {
			Class<ModuleData> clz = instanceMap2.get(clazz);
			if (clz != null){
				return (ModuleData) clz.forName(clz.getName()).newInstance();
			}
			Class<ModuleData> c = (Class<ModuleData>)Class.forName(clazz);
			instanceMap2.put(clazz, c);
			return (ModuleData) c.forName(c.getName()).newInstance();
		} catch (Exception e) {
			//logger.error("err",e);
		}
		return null;
	}
	
	
	/**
	 * 根据cmd返回class
	 * @author Samsung
	 * @throws ClassNotFoundException 
	 *
	 */
	@SuppressWarnings("unchecked")
	private static Class<ModuleData> getClassInfo(String pack, String model) throws ClassNotFoundException{
		if (model.startsWith("/")){
			model = model.substring(1);
		}
		Class<ModuleData> clz = instanceMap1.get(model);
		if (clz != null){
			return clz;
		}
		String className = "";
		String tmp = model;
		String subPackageName = "";
		if (model.indexOf("/") > 0){
			subPackageName =  model.substring(0,model.indexOf("/"));
			tmp = model.substring(model.indexOf("/") + 1);
		}
		if (StringUtils.isNotBlank(subPackageName)){
			pack += "." + subPackageName;
		}
		String[] strArr = tmp.split("_");
		for (int i = 0; i < strArr.length; i++) {
			//首字母大字
			String word = strArr[i].substring(0, 1).toUpperCase();
			word += strArr[i].substring(1);
			className += word;
		}
		className += "Model";
		clz = ((Class<ModuleData>)Class.forName(pack + "." + className));
		instanceMap1.put(model, clz);
		return clz;
	}
}
