package com.wzxy.service.auth;

import net.sf.json.JSONArray;


/**
 * 用户操作认证
 * 
 * @author gull
 *
 */
public interface IAuthService {
	
	/**
	 * 获取所有菜单
	 */
	public JSONArray getAllMenus();
	
	/**
	 * 获取用户操作菜单
	 * 
	 * @param uid
	 * @return
	 */
	public JSONArray getMenu(int uid);
	
	/**
	 * 判断用户的动作是否有权限调用
	 * 
	 * @param uid
	 * @param actionId
	 * @return
	 */
	public boolean isCall(int uid, int actionId);
	
	/**
	 * 重新加载用户权限
	 * 
	 * @param uid
	 */
	public void reload(int uid);
	
}
