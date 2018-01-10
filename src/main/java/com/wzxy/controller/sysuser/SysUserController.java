package com.wzxy.controller.sysuser;

import cn.assist.easydao.pojo.PagePojo;
import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.constant.BaseConstant;
import com.wzxy.base.constant.SysUserConstant;
import com.wzxy.base.utils.JsonBean;
import com.wzxy.base.utils.MD5;
import com.wzxy.base.utils.ReqUtils;
import com.wzxy.base.utils.ShiroUtils;
import com.wzxy.controller.BaseController;
import com.wzxy.service.sys.sysuser.ISysUserService;
import com.wzxy.service.vo.sys.SysAction;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 系统用户管理
 * 
 * @author caibin
 *
 */
@Controller
@RequestMapping(value = "/sysuser")
public class SysUserController extends BaseController {

	@Autowired
	private ISysUserService sysUserService;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model){
		return "modules/sysuser/index";
	}
	
	/**
	 * 
	 * datagrid 绑定数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/list")
	@ResponseBody
	public JSONObject data(HttpServletRequest request, SysAction.SysUser sysUser){
		//参数获取
		int pageNo = ReqUtils.getParamToInt(request, "pageNo", 1);
		int pageSize = ReqUtils.getParamToInt(request, "pageSize", 15);

		String name = ReqUtils.getParam(request,"name",null);
		String realName = ReqUtils.getParam(request,"realName",null);
		String mobile = ReqUtils.getParam(request,"mobile",null);
		String createTime = ReqUtils.getParam(request,"createTime",null);
		String lastLoginTime = ReqUtils.getParam(request,"lastLoginTime",null);
		Integer isValid = ReqUtils.getParamToInteger(request,"isValid",null);

		Map<String,Object> map = new HashMap<String,Object>();
		map.put("name",name);
		map.put("realName",realName);
		map.put("mobile",mobile);
		map.put("createTime",createTime);
		map.put("lastLoginTime",lastLoginTime);
		map.put("isValid",isValid);
		PagePojo<SysAction.SysUser> page = sysUserService.getSysUsers(map, pageNo, pageSize);
		
		//render结果
		return JsonBean.success("success!",page);
	}
	
	/**
	 * 
	 * 添加数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/add")
	@ResponseBody
	public JSONObject add(HttpServletRequest request, SysAction.SysUser sysUser){
		if(StringUtils.isBlank(sysUser.getName())){
			return JsonBean.error("用户名必填");
		}
		if(StringUtils.isBlank(sysUser.getPswd())){
			return JsonBean.error("密码必填");
		}
		if(StringUtils.isBlank(sysUser.getRealName())){
			return JsonBean.error("真实姓名必填");
		}
		if(StringUtils.isBlank(sysUser.getMobile())){
			return JsonBean.error("手机号必填");
		}
		if(StringUtils.isBlank(sysUser.getQq())){
			return JsonBean.error("联系qq必填");
		}
		if(sysUserService.getSysUser(sysUser.getName()) != null){
			return JsonBean.error("用户名已被占用");
		}
		
		int loginUid = ShiroUtils.getUserId();
		Date date = new Date();
		sysUser.setCreateTime(date);	//创建时间
		sysUser.setLastLoginTime(date); //最后登录时间
		sysUser.setCreateUid(loginUid); //创建人
		sysUser.setType(SysUserConstant.USET_TYPE_ROOT);
		
		boolean result = sysUserService.addSysUsers(sysUser);
		
		return result ? JsonBean.success("添加成功") : JsonBean.error("添加失败");
	}
	
	/**
	 * 
	 * 编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/editView")
	public String editView(HttpServletRequest request, Model model){
		Integer uid = ReqUtils.getParamToInteger(request, "uid", null);
		if(uid != null){
			SysAction.SysUser sysUser = sysUserService.getSysUser(uid);
			model.addAttribute("data",sysUser);
			return  "modules/sysuser/_edit";
		}
		return null;
	}
	
	/**
	 * 
	 * 编辑数据
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/edit")
	@ResponseBody
	public JSONObject edit(HttpServletRequest request, SysAction.SysUser sysUser){
		Integer isValid = ReqUtils.getParamToInteger(request,"isValid",0);
		System.out.println(isValid);
		sysUser.setIsValid(isValid);
		sysUser.setUpdateTime(new Date());
		boolean result = sysUserService.editSysUsers(sysUser);
		return result ? JsonBean.success("更新成功") : JsonBean.error("更新失败");
	}

	/**
	 *
	 * 删除用户数据
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/del")
	@ResponseBody
	public JSONObject del(HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
 		int result = sysUserService.delSysUsers(ids);
		return result > 0 ?JsonBean.success("删除成功") : JsonBean.error("删除失败");
	}

	/**
	 *
	 * 修改密码
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/updatePwd")
	@ResponseBody
	public JSONObject updatePwd(HttpServletRequest request, SysAction.SysUser sysUser){
		Integer uid = (Integer) request.getSession().getAttribute(BaseConstant.SYS_UID);
		sysUser.setUid(uid);
		boolean result = sysUserService.updatePwd(sysUser);
		if (result) {
			request.getSession().removeAttribute(BaseConstant.SYS_UID);
		}
		return result ? JsonBean.success("更新成功") : JsonBean.error("更新失败");
	}

	/**
	 *
	 *获取修改密码页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getUserInfo")
	public String getPwdView(HttpServletRequest request,Model model){
		Integer uid = ShiroUtils.getUserId();
		SysAction.SysUser sysUser = sysUserService.getSysUser(uid);
		model.addAttribute("data",sysUser);
		return "modules/sysuser/_info";
	}

	/**
	 *
	 *获取修改密码页面
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/checkPwd")
	@ResponseBody
	public JSONObject checkPwd(HttpServletRequest request){
		Integer uid = ShiroUtils.getUserId();
		String pwd = ReqUtils.getParam(request,"pwd",null);
		if (StringUtils.isBlank(pwd)) {
			return JsonBean.error("解锁密码不能为空！");
		}
		SysAction.SysUser sysUser = sysUserService.getSysUser(uid);
		if (MD5.encode(pwd).equalsIgnoreCase(sysUser.getPswd())) {
			return JsonBean.success("解锁成功！");
		}
		return JsonBean.error("解锁密码不正确！");
	}
}
