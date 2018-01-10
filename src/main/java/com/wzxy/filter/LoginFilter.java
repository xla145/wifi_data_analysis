package com.wzxy.filter;

import com.alibaba.fastjson.JSONObject;
import com.wzxy.service.vo.sys.SysAction;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 登录filter
 * 
 * @author caibin
 *
 */
public class LoginFilter implements Filter{

	static List<String> excludeList = new ArrayList<String>();
	static{
		excludeList.add("/login");
		excludeList.add("/getCode");
		excludeList.add("/resources2.0/");
	}
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	public void doFilter(ServletRequest request, ServletResponse response,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;
		//排除不需要登录状态的资源
		String servletPath = httpRequest.getServletPath();
		if (BaseConstant.isDev) {//开发环境不用登录
			httpRequest.getSession().setAttribute(BaseConstant.SYS_UID,1);
			SysAction.SysUser sysUser = new SysAction.SysUser();
			sysUser.setName("admin");
			httpRequest.getSession().setAttribute(BaseConstant.SYS_USER, JSONObject.toJSON(sysUser).toString());
			chain.doFilter(request, response);
			return;
		}
		for (String exclude : excludeList) {
			if(servletPath.startsWith(exclude)){
				chain.doFilter(request, response);
				return;
			}
		}
		//登录状态判断
		if(httpRequest.getSession().getAttribute(BaseConstant.SYS_UID) == null){
			httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.html");
			return;
		}
		chain.doFilter(request, response);
		
	}

	public void destroy() {
		
	}

}
