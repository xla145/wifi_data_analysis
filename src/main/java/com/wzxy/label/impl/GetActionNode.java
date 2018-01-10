package com.wzxy.label.impl;
import com.wzxy.base.helper.SpringFactory;
import com.wzxy.label.ModuleData;
import com.wzxy.service.auth.ISysActionService;
import com.wzxy.service.vo.Option;
import com.wzxy.service.vo.sys.SysAction;
import freemarker.template.TemplateModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 
 * 获取下拉框数据
 * 
 * @author caibin
 *
 */
public class GetActionNode extends ModuleData{

	@Override
	public Map<String, Object> getModelData(Map<String, Object> params) throws Exception {
		TemplateModel checked = (TemplateModel) params.get("checked");
		ISysActionService sysActionService = SpringFactory.getBean("ISysActionService");
		List<SysAction> list = sysActionService.getParentMenus();
		List<Option> options = new ArrayList<Option>();
		if(list != null){
			for (SysAction sysAction : list) {
				Option option = new Option();
				option.setId(sysAction.getId()+"");
				option.setName(sysAction.getName());
				options.add(option);
			}
		}
		if (checked != null) {
			root.put("checked",checked.toString());
		}
		root.put("options", options);
		return root;
	}

}
