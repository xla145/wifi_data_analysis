package com.wzxy.controller.sysauth;

import cn.assist.easydao.common.Conditions;
import cn.assist.easydao.common.SqlExpr;
import cn.assist.easydao.pojo.PagePojo;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.logistics.base.utils.JsonBean;
import com.logistics.base.utils.ReqUtils;
import com.logistics.service.auth.ISysActionService;
import com.logistics.service.auth.impl.AuthServiceImpl;
import com.logistics.service.vo.Option;
import com.logistics.service.vo.sys.SysAction;
import com.logistics.service.vo.sys.TreeNode;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 系统功能管理
 * 
 * @author caibin
 *
 */
@Controller
@RequestMapping(value = "/sysAction")
public class SysActionsController {

	@Autowired
	private ISysActionService sysActionService;
	
	@RequestMapping(value = "/index")
	public String index(HttpServletRequest request, Model model){
		return "modules/sys-auth/action/index";
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
	public JSONObject data(HttpServletRequest request){
		//参数获取
		int pageNo = ReqUtils.getParamToInt(request, "pageNo", 1);
		int pageSize = ReqUtils.getParamToInt(request, "pageSize", 15);
		String name = ReqUtils.getParam(request,"name",null);
		Conditions conn = new Conditions("name", SqlExpr.EQUAL,name);
		PagePojo<SysAction> page = sysActionService.getSysAction(conn, pageNo, pageSize);
		return JsonBean.success("",page);
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
	public JSONObject add(HttpServletRequest request, SysAction sysAction){
		if(StringUtils.isBlank(sysAction.getName())){
			return JsonBean.error("功能名称必填");
		}
		if(sysAction.getType() == null){
			return JsonBean.error("功能类型必选");
		}
		if(sysAction.getParentId() == null){
			return JsonBean.error("父级节点必选");
		}
		if(sysAction.getType() == AuthServiceImpl.ACTION_TYPE_MENU){
			if(StringUtils.isBlank(sysAction.getUrl())){
				return JsonBean.error("系统菜单url必填");
			}
			if(StringUtils.isBlank(sysAction.getRemark())){
				return JsonBean.error("功能描述必填");
			}
		}
	
		sysAction.setCreateTime(new Date());	//创建时间
		
		boolean result = sysActionService.addSysAction(sysAction);
		
		return result ? JsonBean.success("添加成功") : JsonBean.error("添加失败");
	}
	
	/**
	 * 
	 * 编辑页面
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/getView")
	public String editView(HttpServletRequest request, Model model){
		Integer id = ReqUtils.getParamToInteger(request, "id", null);
		SysAction sysAction = new SysAction();
		List<SysAction> list = sysActionService.getParentMenus();
		List<Option> options = new ArrayList<Option>();
		if(list != null){
			for (SysAction sa : list) {
				Option option = new Option();
				option.setId(sa.getId()+"");
				option.setName(sa.getName());
				options.add(option);
			}
		}
		model.addAttribute("options", options);
		if(id != null){
			sysAction = sysActionService.getSysAction(id);
			model.addAttribute("data", sysAction);

		} else {
			model.addAttribute("data", sysAction);
		}
		return "modules/sys-auth/action/_add";
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
	public JSONObject edit(HttpServletRequest request, SysAction sysAction){
		if(StringUtils.isBlank(sysAction.getName())){
			return JsonBean.error("功能名称必填");
		}
		if(sysAction.getType() == null){
			return JsonBean.error("功能类型必选");
		}
		if(sysAction.getParentId() == null){
			return JsonBean.error("父级节点必选");
		}
		if(sysAction.getType() == AuthServiceImpl.ACTION_TYPE_MENU){
			if(StringUtils.isBlank(sysAction.getUrl())){
				return JsonBean.error("系统菜单url必填");
			}
			if(StringUtils.isBlank(sysAction.getRemark())){
				return JsonBean.error("功能描述必填");
			}
		}
		boolean result = sysActionService.editSysAction(sysAction);
		return result ? JsonBean.success("更新成功") : JsonBean.error("更新失败");
	}

	@RequestMapping(value = "/del")
	@ResponseBody
	public JSONObject del(HttpServletRequest request){
		String[] ids = request.getParameterValues("ids");
		boolean result = sysActionService.delSysAction(ids);
		return result ? JsonBean.success("删除成功") : JsonBean.error("删除失败");
	}

	@RequestMapping(value = "/catIcon")
	public String catIcon(HttpServletRequest request){
		return "modules/sys-auth/action/fontclass";
	}



	@RequestMapping(value = "/tree")
	public String tree(HttpServletRequest request, Model model){
		List<TreeNode> list = sysActionService.getActionTrees();
		model.addAttribute("treeNode", JSONArray.toJSONString(list));
		return "modules/sys-auth/action/_tree";
	}
}
