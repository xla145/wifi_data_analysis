package com.wzxy.label;
import freemarker.core.Environment;
import freemarker.template.*;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

/**
 * 自定义模板基类
 * 
 * @author caibin
 *
 */
public class MyDirective implements TemplateDirectiveModel {
	private Logger logger = LoggerFactory.getLogger(MyDirective.class);
	
	@Autowired
	public HttpServletRequest request;
	@Autowired  
	public ApplicationContext applicationContext;  
	
	/**
	 * 默认模板路径起始位置
	 */
	public static final String DEFAULT_TEMP_BASE_PATH = "/modules/";
	public static final String DEFAULT_TEMP_BASE_DIR = "_modules/";
	
	
	/**
	 * 默认包路径
	 *
	 */
	public static final String DEFAULT_MODEL_PATH = " com.wzxy.label.impl";

	
	@SuppressWarnings("rawtypes") 
	public void execute(Environment env, Map params, TemplateModel[] loopVars,TemplateDirectiveBody body) throws TemplateException, IOException {
		try {
			TemplateModel tempName = ((TemplateModel) params.get("name"));		//模块名称 不能为空
			TemplateModel tempClazz = ((TemplateModel) params.get("class"));	//模块操作类路径，可为空，默认以模块名作为操作类名，默认在“com.cnm.iot.label.impl”包下
			TemplateModel tempDir = ((TemplateModel) params.get("dir"));		//模板文件路径，为空则默认在	/view/modules/_model/下

			if(tempName == null){
				return ;
			}
			Template template = getTemplate(env, tempDir, tempName);
			if(template == null){
				return ;
			}
			@SuppressWarnings("unchecked")
			ModuleData data = createMd(tempClazz, tempName);
			if (data == null) {
				return;
			}
//			System.out.println(data);
			Map<String, Object> root = data.getModelData(params);
			if(root == null){
				root = new HashMap<String, Object>();
			}
			//合并数据模型和模版  
	        Writer out = env.getOut();
	        template.process(root, out);
	        
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("", e.getMessage());
		}
	}
	
	
	protected ModuleData createMd(TemplateModel tempClazz, TemplateModel tempName){
		try {
			String clazz = null;
			if(tempClazz != null){
				clazz = tempClazz.toString();
			}
			if(StringUtils.isNotBlank(clazz)){
				return ClazzFactory.getOperator(clazz);
			}else{
				return ClazzFactory.getOperator(DEFAULT_MODEL_PATH, tempName.toString());
			}
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
	
	protected Template getTemplate(Environment env, TemplateModel tempDir, TemplateModel tempName) {
		try {
			String tempPath = DEFAULT_TEMP_BASE_PATH;
			if(tempDir != null){
				tempPath = tempPath + tempDir.toString();
			}else{
				tempPath = tempPath + DEFAULT_TEMP_BASE_DIR + tempName.toString();
			}
			tempPath = tempPath + ".html";
			Configuration freemarkerConf = env.getConfiguration();
			return freemarkerConf.getTemplate(tempPath);
		} catch (Exception e) {
			logger.error("", e);
		}
		return null;
	}
}
