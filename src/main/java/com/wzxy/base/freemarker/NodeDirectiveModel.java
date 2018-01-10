package com.wzxy.base.freemarker;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;

import java.io.IOException;
import java.util.Map;

/**
 * 权限节点拦截处理指令
 * 
 * @author caibin
 *
 */
public class NodeDirectiveModel implements TemplateDirectiveModel {

	/**
	 * 是否拥有该权限
	 * 权限标识
	 * @return   true：是     false：否
	 */
	@SuppressWarnings("rawtypes")
	public void execute(Environment env,Map params, TemplateModel[] loopVars,
						TemplateDirectiveBody body) throws TemplateException, IOException {
		if (params == null || params.size() == 0) {
			throw new TemplateException("params can not be empty", env);
		}
		TemplateModel paramValue = (TemplateModel) params.get("permission");
		String permission = paramValue.toString();
		Subject subject = SecurityUtils.getSubject();
		if(body != null && (subject != null && subject.isPermitted(permission))){
			body.render(env.getOut());
		}
	}

}
