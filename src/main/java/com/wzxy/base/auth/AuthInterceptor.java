package com.wzxy.base.auth;

import com.wzxy.base.utils.JsonBean;
import com.wzxy.service.auth.IAuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



/**
 * 权限验证
 * 
 * @author gull
 *
 */
public class AuthInterceptor implements HandlerInterceptor{

	@Autowired
	private IAuthService authService;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		if(handler instanceof HandlerMethod){
	        HandlerMethod method = (HandlerMethod)handler;  
	        Auth auth = method.getMethodAnnotation(Auth.class);  
	        if (auth == null) {  
	            return true;  
	        }
	        int uid = (Integer)request.getSession().getAttribute(BaseConstant.SYS_UID);
	        if(uid == 1){ //admin 忽视任何权限判断
	        	return true;
	        }
	        int actionId = auth.id();
	        
	        //权限验证
	        if(authService.isCall(uid, actionId)){
	        	if("JSONObject".equalsIgnoreCase(method.getMethod().getReturnType().getSimpleName())){
	        		response.setCharacterEncoding("UTF-8");
	        		response.setHeader("Content-type", "application/json;charset=UTF-8");
	        		response.getWriter().write(JsonBean.error(JsonBean.ERR, "对不起，您没有权限").toString());
	        	}else{
	        		response.sendRedirect("/no_permission");
	        	}
	            return false;  
	        }
		}
		return true;
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		
	}

	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
