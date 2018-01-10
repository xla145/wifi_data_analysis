package com.wzxy.service.auth.impl;

import cn.assist.easydao.common.*;
import cn.assist.easydao.dao.BaseDao;
import com.wzxy.base.cache.MCacheKit;
import com.wzxy.service.auth.IAuthService;
import com.wzxy.service.vo.sys.SysAction;
import com.wzxy.service.vo.sys.SysUserAction;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * 用户操作认证
 * 
 * @author gull
 *
 */
@Service("IUserAuthService")
public class AuthServiceImpl implements IAuthService {
	
	/**1：系统功能 **/
	public static int ACTION_TYPE_BTN = 1;
	/**2：导航菜单 **/
	public static int ACTION_TYPE_MENU = 2;
	
	private Logger logger = LoggerFactory.getLogger(AuthServiceImpl.class);
	private static String DEFAULTAUTHKEY = "com.cnm.iot.service.auth.impl.UserAuthServiceImpl";
	
	
	/**
	 * 获取导航菜单
	 */
	public JSONArray getAllMenus() {
		String key = DEFAULTAUTHKEY + ".getAllMenus";
		JSONArray array = MCacheKit.get(key);
		if(array != null){
			return array;
		}
		
		Conditions conn = new Conditions("type", SqlExpr.EQUAL, AuthServiceImpl.ACTION_TYPE_MENU);
		conn.add(new Conditions("status",SqlExpr.EQUAL,1),SqlJoin.AND);
		Sort sort = new Sort("weight",SqlSort.ASC);
		List<SysAction> list = BaseDao.dao.queryForListEntity(SysAction.class, conn,sort);
		JSONArray navArr = new JSONArray();
		
		if(list != null){
			for (SysAction sa : list) {
				if(sa.getParentId() < 1){
					JSONObject nodeJson = new JSONObject();
					nodeJson.put("title", sa.getName());
					nodeJson.put("icon", sa.getIcon());
					nodeJson.put("spread", false);
					JSONArray childrens = new JSONArray();
					for (SysAction sa2 : list) {
						if(sa2.getParentId() == sa.getId()){
							JSONObject children = new JSONObject();
							children.put("title", sa2.getName());
							children.put("icon", sa2.getIcon());
							children.put("href", sa2.getUrl());
							childrens.add(children);
						}
					}
					nodeJson.put("children", childrens);
					navArr.add(nodeJson);
				}
			}
		}
		if(navArr != null && navArr.size() > 0){
			MCacheKit.add(key, 60*10, navArr);
		}
		return navArr;
	}
	
	/**
	 * 获取用户操作菜单
	 * 
	 * @param uid
	 * @return
	 */
	public JSONArray getMenu(int uid) {
		String key = DEFAULTAUTHKEY + ".getUserMenus." + uid;
		JSONArray array = MCacheKit.get(key);
		if(array == null){
			array = this.getUserMenus(uid, key);
		}
		return array;
	}
	
	/**
	 * 判断用户的动作是否有权限调用
	 * 
	 * @param uid
	 * @param actionId
	 * @return
	 */
	public boolean isCall(int uid, int actionId){
		String key = DEFAULTAUTHKEY + ".isCall." + uid;
		Map<Integer, Boolean> map = MCacheKit.get(key);
		if(map == null || map.size() < 1){
			map = this.getuserActions(uid, key);
		}
		return map == null ? false : map.get(actionId) == null ? false : true;
	}
	
	/**
	 * 重新加载用户权限
	 * 
	 * @param uid
	 */
	public void reload(int uid){
		String key = DEFAULTAUTHKEY + ".isCall." + uid;
		String key2 = DEFAULTAUTHKEY + ".getUserMenus." + uid;
		String key3 = DEFAULTAUTHKEY + ".getAllMenus";
		MCacheKit.delete(key);
		MCacheKit.delete(key2);
		MCacheKit.delete(key3);
		this.getuserActions(uid, key);
		this.getUserMenus(uid, key2);
		this.getAllMenus();
		logger.info("reload auth success uid:" + uid); 
	}
	
	
	/**
	 * 获取用户操作列表
	 * 
	 * @param uid
	 * @param key
	 * @return
	 */
	private Map<Integer, Boolean> getuserActions(int uid, String key){
		Map<Integer, Boolean> map = new HashMap<Integer, Boolean>();
		Conditions conn = new Conditions("uid", SqlExpr.EQUAL, uid);
		List<SysUserAction> list = BaseDao.dao.queryForListEntity(SysUserAction.class, conn);
		if(list != null){
			map = new HashMap<Integer, Boolean>();
			for (SysUserAction sysUserAction : list) {
				map.put(sysUserAction.getActionId(), true);
			}
			MCacheKit.add(key, 60*60, map);
		}
		return map;
	}

	/**
	 * 获取用户菜单列表
	 * 
	 * @param uid
	 * @param key
	 * @return
	 */
	private JSONArray getUserMenus(int uid, String key){
		JSONArray array = new JSONArray();
		String sql = "select sa.* from sys_action as sa inner join sys_user_action as sua on sa.id = sua.action_id where sua.uid = ? order by sa.weight asc ";
		List<SysAction> list = BaseDao.dao.queryForListEntity(SysAction.class, sql, uid);
		if(list == null || list.size() < 1){
			return null;
		}
		for (SysAction sa : list) {
			if(sa.getParentId() < 1){
				JSONObject json = new JSONObject();
				json.put("title", sa.getName());
				json.put("icon", sa.getIcon());
				JSONArray arr = new JSONArray();
				for (SysAction sa2 : list) {
					if(sa2.getParentId() == sa.getId()){
						JSONObject children = new JSONObject();
						children.put("title", sa2.getName());
						children.put("icon", sa2.getIcon());
						children.put("href", sa2.getUrl());
						arr.add(children);
					}
				}
				json.put("children", arr);
				array.add(json);
			}
		}
		if(array.size() > 0){
			MCacheKit.add(key, 60*60, array);
		}
		return array;
	}
}
