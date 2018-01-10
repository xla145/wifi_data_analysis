package com.wzxy.base.utils;

import com.alibaba.fastjson.JSONObject;


/**
 * response json格式
 * 
 * @author caibin
 *
 */
public class TableData {

	public static final int ERR = -1;
	public static final int OK = 0;

	public static JSONObject error(String msg) {
		JSONObject json = new JSONObject();
		json.put("code", JsonBean.ERR);
		json.put("msg", msg);
		json.put("count", 0);
		return json;
	}
	
	public static JSONObject success(String msg, int count, Object object) {
		JSONObject json = new JSONObject();
		json.put("code", JsonBean.OK);
		json.put("msg", msg);
		json.put("count", count);
		json.put("data", object);
		return json;
	}
}
