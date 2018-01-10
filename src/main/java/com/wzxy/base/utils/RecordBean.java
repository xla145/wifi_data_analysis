package com.wzxy.base.utils;


/**
 * 响应请求消息对象
 * 
 * @author caibin
 * @param <T>
 *
 */
public class RecordBean<T> {

	public static final int ERROR = -1;//失败
	public static final int OK = 0; // 成功
	
	
	private int code;
	private String msg;
	private T data;
	
	RecordBean(int code, String msg){
		this.code = code;
		this.msg = msg;
	}
	RecordBean(int code, String msg, T data){
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public boolean isSuccessCode() {
		return this.code == OK;
	}
	
	public static <T> RecordBean<T>  error(String msg) {
		return new RecordBean<T>(ERROR, msg);
	}
	public static <T> RecordBean<T> error(String msg, T data) {
		return new RecordBean<T>(ERROR, msg, data);
	}
	
	public static <T> RecordBean<T> success(String msg) {
		return new RecordBean<T>(OK, msg);
	}
	public static <T> RecordBean<T> success(String msg, T data) {
		return new RecordBean<T>(OK, msg, data);
	}
	
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	
}
