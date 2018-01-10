package com.wzxy.base.freemarker;
import com.wzxy.base.utils.ShiroUtils;
import com.wzxy.service.sys.sysuser.ISysUserService;
import com.wzxy.service.vo.sys.SysAction;
import freemarker.core.Environment;
import freemarker.ext.beans.BeansWrapper;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Map;

/**
 * 显示用户登录名
 * 
 * @author caibin
 *
 */
public class ShowUserDirectiveModel implements TemplateDirectiveModel {

	@Autowired
	private ISysUserService sysUserService;
	@Autowired
	private HttpServletRequest request;
	
	
	@SuppressWarnings("rawtypes") 
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		int uid = ShiroUtils.getUserId();
		SysAction.SysUser sysUser = sysUserService.getSysUser(uid);
		if(sysUser == null){
			return;
		}
		
		if(body != null){
			env.setVariable("sysUser", new BeansWrapper().wrap(sysUser));
	        body.render(env.getOut());
		}else{
			env.getOut().write(sysUser.getRealName());
		}
	}
}
