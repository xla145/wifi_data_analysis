package com.wzxy.service.auth;

import cn.assist.easydao.common.Conditions;
import cn.assist.easydao.pojo.PagePojo;
import com.wzxy.base.utils.RecordBean;
import com.wzxy.service.vo.sys.SysRole;
import com.wzxy.service.vo.sys.SysRoleAction;
import com.wzxy.service.vo.sys.SysUserRole;
import com.wzxy.service.vo.sys.TreeNode;

import java.util.List;


/**
 * 系统权限
 * 
 * @author gull
 *
 */
public interface ISysRoleService {
	
	/**
	 * 查询角色列表
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysRole> getSysRoles();

	/**
	 * 查询角色列表
	 *
	 * @param roleId
	 * @return
	 */
	public SysRole getSysRole(Integer roleId);

	/**
	 * 查询角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysRoleAction> getSysRoleAction(int roleId);
	
	/**
	 * 查询用户角色列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<SysUserRole> getSysUserRole(int uid);
	
	/**
	 * 查询角色权限树
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<TreeNode> getRoleActionTree(List<Integer> roleIds);
	
	/**
	 * 更新角色权限
	 * 
	 * @param roleId
	 * @param sysRoleActions
	 * @return
	 */
	public boolean reloadSysRoleAction(int roleId, int operateUid, List<SysRoleAction> sysRoleActions);


	/**
	 * 分页获取角色信息
	 * @param conn
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PagePojo<SysRole> getSysRolePage(Conditions conn, Integer pageNo, Integer pageSize);


	/**
	 * 添加角色信息
	 * @param sysRole
	 * @return
	 */
	public RecordBean<SysRole> addSysRole(SysRole sysRole);

	/**
	 * 修改角色信息
	 * @param sysRole
	 * @return
	 */
	public RecordBean<SysRole> editSysRole(SysRole sysRole);

	/**
	 * 删除角色信息
	 * @param ids
	 * @return
	 */
	public RecordBean<String> delSysRole(String[] ids);
}
