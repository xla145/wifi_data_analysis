package com.wzxy.label;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * @author caibin
 * 
 */
public abstract class ModuleData{
	static Logger logger = LoggerFactory.getLogger(ModuleData.class);
	
	public Map<String, Object> root = new HashMap<String, Object>();
	
	public abstract Map<String, Object> getModelData(Map<String, Object> params) throws Exception;
	
}
