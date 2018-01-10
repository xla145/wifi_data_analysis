package com.wzxy.service.auth;

import cn.assist.easydao.common.Conditions;
import cn.assist.easydao.pojo.PagePojo;
import com.wzxy.service.vo.sys.SysAction;
import com.wzxy.service.vo.sys.SysUserAction;
import com.wzxy.service.vo.sys.TreeNode;

import java.util.List;


/**
 * 系统权限
 * 
 * @author gull
 *
 */
public interface ISysActionService {
	
	/**
	 * 获取菜单详细信息
	 */
	public List<TreeNode> getActionTrees();


	/**
	 * 获取菜单详细信息
	 */
	public List<SysAction> getAllAction();

	/**
	 * 获取菜单详细信息
	 */
	public SysAction getSysAction(int id);
	
	/**
	 * 获取父级菜单
	 */
	public List<SysAction> getParentMenus();
	
	/**
	 * 查询用户角色权限列表
	 * 
	 * @param
	 * @return
	 */
	public List<SysUserAction> getSysUserAction(int uid);


	/**
	 * 查询用户角色权限列表
	 *
	 * @param
	 * @return
	 */
	public List<SysAction> getSysUserActionByUid(int uid);

	/**
	 * 更新用户权限
	 * 
	 * @param uid
	 * @param roleIds
	 * @return
	 */
	public boolean reloadSysUserAction(int operateUid, int uid, List<Integer> roleIds);
	
	/**
	 * 分页查询系统功能列表
	 * 
	 * @param
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PagePojo<SysAction> getSysAction(Conditions conn, int pageNo, int pageSize);
	
	
	/**
	 * 添加系统功能
	 * 
	 * @param
	 * @return
	 */
	public boolean addSysAction(SysAction sysAction);
	
	/**
	 * 编辑系统功能
	 * 
	 * @param
	 * @return
	 */
	public boolean editSysAction(SysAction sysAction);

	/**
	 * 删除系统功能
	 * @param ids
	 * @return
	 */
	public boolean delSysAction(String[] ids);
	
}
