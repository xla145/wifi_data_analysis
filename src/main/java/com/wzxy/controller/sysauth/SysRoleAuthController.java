package com.wzxy.controller.sysauth;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.wzxy.base.utils.JsonBean;
import com.wzxy.base.utils.ReqUtils;
import com.wzxy.base.utils.ShiroUtils;
import com.wzxy.service.auth.ISysActionService;
import com.wzxy.service.auth.ISysRoleService;
import com.wzxy.service.vo.sys.SysRole;
import com.wzxy.service.vo.sys.SysRoleAction;
import com.wzxy.service.vo.sys.TreeNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 角色权限管理
 * 
 * @author caibin
 *
 */
@Controller
@RequestMapping(value = "/sysRoleAuth")
public class SysRoleAuthController {

	@Autowired
	private ISysActionService sysActionService;
	@Autowired
	private ISysRoleService sysRoleService;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model){
		List<TreeNode> list = sysActionService.getActionTrees();
		List<SysRole> roleList = sysRoleService.getSysRoles();
		
		model.addAttribute("roleList", roleList);
		model.addAttribute("treeNode", JSONArray.toJSONString(list));
		return "modules/sys-auth/role-auth/index";
	}
	
	//获取角色权限列表
	@RequestMapping(value = "/getRoleAction")
	@ResponseBody
	public JSONObject getRoleAction(HttpServletRequest request){
		int roleId = ReqUtils.getParamToInt(request, "roleId", 0);
		if(roleId < 1){
			return JsonBean.error("角色不存在!");
		}
		JSONArray arr = new JSONArray();
		List<SysRoleAction> list = sysRoleService.getSysRoleAction(roleId);
		if(list != null){
			for (SysRoleAction sysRoleAction : list) {
				arr.add(sysRoleAction.getActionId());
			}
		}
		return JsonBean.success("ok", arr);
	}
	
	//更新角色权限
	@RequestMapping(value = "/editRoleAction")
	@ResponseBody
	public JSONObject editRoleAction(HttpServletRequest request,@RequestParam("actionIds[]") List<Integer> actionIds){
		int uid = ShiroUtils.getUserId();
		int roleId = ReqUtils.getParamToInt(request, "roleId", 0);
		if(roleId < 1){
			return JsonBean.error("角色不存在!");
		}
		if(actionIds == null || actionIds.size() < 1){
			return JsonBean.error("未选择功能菜单！");
		}
		List<SysRoleAction> list = new ArrayList<SysRoleAction>();
		Date date = new Date();
		for (int actionId : actionIds) {
			SysRoleAction sra = new SysRoleAction();
			sra.setActionId(actionId);
			sra.setRoleId(roleId);
			sra.setCreateTime(date);
			sra.setCreateUid(uid);
			list.add(sra);
		}
		boolean result = sysRoleService.reloadSysRoleAction(roleId, uid, list);
		return result ? JsonBean.success("更新角色权限成功") : JsonBean.error("更新角色权限失败");
	}
}
