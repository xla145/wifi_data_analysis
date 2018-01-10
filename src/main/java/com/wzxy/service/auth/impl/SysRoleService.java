package com.wzxy.service.auth.impl;

import cn.assist.easydao.common.Conditions;
import cn.assist.easydao.common.SqlExpr;
import cn.assist.easydao.dao.BaseDao;
import cn.assist.easydao.pojo.PagePojo;
import com.wzxy.base.utils.CommonUtil;
import com.wzxy.base.utils.DateUtil;
import com.wzxy.base.utils.RecordBean;
import com.wzxy.service.auth.ISysRoleService;
import com.wzxy.service.vo.sys.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


/**
 * 系统权限
 * 
 * @author gull
 *
 */
@Service("ISysRoleService")
public class SysRoleService implements ISysRoleService {

	/**
	 * 查询所有角色
	 * 
	 */
	public List<SysRole> getSysRoles() {
		Conditions conn = new Conditions("id", SqlExpr.GT, 0);
		return BaseDao.dao.queryForListEntity(SysRole.class, conn);
	}

	@Override
	public SysRole getSysRole(Integer roleId) {
		return BaseDao.dao.queryForEntity(SysRole.class,roleId);
	}

	/**
	 * 查询角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	public List<SysRoleAction> getSysRoleAction(int roleId){
		Conditions conn = new Conditions("role_id", SqlExpr.EQUAL, roleId);
		return BaseDao.dao.queryForListEntity(SysRoleAction.class, conn);
	}
	
	/**
	 * 查询用户角色列表
	 * 
	 * @param uid
	 * @return
	 */
	public List<SysUserRole> getSysUserRole(int uid) {
		Conditions conn = new Conditions("uid", SqlExpr.EQUAL, uid);
		return BaseDao.dao.queryForListEntity(SysUserRole.class, conn);
	}
	
	/**
	 * 查询角色权限树
	 * 
	 * @param roleIds
	 * @return
	 */
	public List<TreeNode> getRoleActionTree(List<Integer> roleIds) {
		List<TreeNode> treeNodes = new ArrayList<TreeNode>();
		if(roleIds == null || roleIds.size() < 1){
			return treeNodes;
		}
		StringBuffer sql = new StringBuffer("SELECT DISTINCT a.* ");
		sql.append(" FROM `sys_action` AS a INNER JOIN `sys_role_action` AS b ON a.`id` = b.`action_id`");
		sql.append(" WHERE b.`role_id` IN(");
		for (int i = 0; i < roleIds.size(); i++) {
			if(i > 0){
				sql.append(",");
			}
			sql.append(roleIds.get(i));
		}
		sql.append(") AND parent_id < 1");
		
		List<SysAction> list = BaseDao.dao.queryForListEntity(SysAction.class, sql.toString());
		if(list != null){
			for (SysAction sysAction : list) {
				TreeNode treeNode = getRoleActionTree(sysAction, roleIds);
				treeNodes.add(treeNode);
			}
		}
		if(treeNodes.size() > 0){
			treeNodes.get(0).setSpread(true);
		}
		return treeNodes;
	}

	/**
	 * 更新角色权限
	 * 
	 * @param roleId
	 * @return
	 */
	@Transactional
	public boolean reloadSysRoleAction(int roleId, int operateUid, List<SysRoleAction> sysRoleActions) {
		String remark = "更新角色权限同步用户权限， operateUid：" +operateUid;
		if(sysRoleActions.size() > 0){
			//清除所有角色权限
			BaseDao.dao.delete("delete from sys_role_action where role_id = "+ roleId);
			//插入新的角色权限
			int i = BaseDao.dao.insert(sysRoleActions);
			Logger logger = LoggerFactory.getLogger(getClass());
			logger.info("更新角色权限－－>插入角色权限，result:" + i + ",roleId:" + roleId + ",operateUid:" + operateUid );
			if(i > 0){
				//删除 所有用户角色权限
				BaseDao.dao.delete("delete from sys_user_action");
				//保存所有用户角色权限
				StringBuffer sql = new StringBuffer("INSERT INTO `sys_user_action`(uid, action_id, role_id, create_time,create_uid,remark)");
				sql.append(" SELECT DISTINCT b.uid AS uid, a.action_id AS action_id, a.role_id as role_id, '" + DateUtil.formatDateToString(new Date()) + "' AS create_time , "+operateUid+" AS create_uid, '"+remark+"' AS remark");
				sql.append(" FROM `sys_role_action` AS a INNER JOIN `sys_user_role` AS b ON a.role_id = b.role_id GROUP BY b.uid,a.action_id");
				int result = BaseDao.dao.insert(sql.toString());
				logger.info("更新角色权限－－>插入用户角色权限，result:" + result + ",roleId:" + roleId + ",operateUid:" + operateUid );
			}
			return i > 0;
		}
		return true;
	}

	@Override
	public PagePojo<SysRole> getSysRolePage(Conditions conn, Integer pageNo, Integer pageSize) {
		return BaseDao.dao.queryForListPage(SysRole.class,conn,null,pageNo,pageSize);
	}

	@Override
	public RecordBean<SysRole> addSysRole(SysRole sysRole) {
		int result = BaseDao.dao.insert(sysRole);
		if (result == 0) {
			return RecordBean.error("添加角色信息失败！");
		}
		return RecordBean.success("",sysRole);
	}

	@Override
	public RecordBean<SysRole> editSysRole(SysRole sysRole) {
		int result = BaseDao.dao.update(sysRole);
		if (result == 0) {
			return RecordBean.error("添加角色信息失败！");
		}
		return RecordBean.success("",sysRole);
	}

	@Override
	public RecordBean<String> delSysRole(String[] ids) {
		StringBuffer sql = new StringBuffer();
		sql.append("DELETE FROM sys_role WHERE id IN(");
		sql.append(CommonUtil.arrayToSqlIn(ids));
		sql.append(")");
		int result = BaseDao.dao.update(sql.toString());
		if (result == 0) {
			return RecordBean.error("删除角色信息失败！");
		}
		return RecordBean.success("");
	}

	/**
	 * 获取功能树
	 * 
	 */
	private TreeNode getRoleActionTree(SysAction sysAction, List<Integer> roleIds) {
		int id = sysAction.getId();
		TreeNode treeNode = new TreeNode();
		treeNode.setId(id);
		treeNode.setName(sysAction.getName());
		treeNode.setParentId(sysAction.getParentId());
		treeNode.setCheckboxValue(String.valueOf(id));
		StringBuffer sql = new StringBuffer("SELECT DISTINCT a.* ");
		sql.append(" FROM `sys_action` AS a INNER JOIN `sys_role_action` AS b ON a.`id` = b.`action_id`");
		sql.append(" WHERE b.`role_id` IN(");
		for (int i = 0; i < roleIds.size(); i++) {
			if(i > 0){
				sql.append(",");
			}
			sql.append(roleIds.get(i));
		}
		sql.append(") AND parent_id = ?");
		List<SysAction> childrenSysActionList =  BaseDao.dao.queryForListEntity(SysAction.class, sql.toString(), id);
		for (SysAction childrenSysAction : childrenSysActionList) {
			//递归查询所有子节点
			TreeNode tn = getRoleActionTree(childrenSysAction, roleIds); 
			treeNode.getChildren().add(tn);
		}
		return treeNode;
	}
}
