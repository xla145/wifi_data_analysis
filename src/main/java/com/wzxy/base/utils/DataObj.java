package com.wzxy.base.utils;


import java.io.Serializable;

public class DataObj<T> implements Serializable {
	private static final long serialVersionUID = 7933714516649991146L;
	
	public static final int CODE_SUCCESS = 0;
	public static final int CODE_FAIL = -99;
	
	private int successCode = CODE_SUCCESS;
	private int code;
	private T data;
	private String msg = "";
	
	public DataObj() {
	}
	/**
	 * 初始化一个失败的DataBean
	 * @param msg
	 */
	public DataObj(String msg) {
		this.code = CODE_FAIL;
		this.msg = msg;
	}
	
	/**
	 * 初始化一个给定code的DataBean
	 * @param msg
	 */
	public DataObj(int code) {
		this.code = code;
	}
	public DataObj(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}
	
	public static <T> DataObj<T> getSuccessData(T data){
		DataObj<T> obj = new DataObj<>(CODE_SUCCESS);
		obj.setSuccessData(data);
		return obj;
	}
	
	public boolean isSuccessCode() {
		return this.code == successCode;
	}
	
	public DataObj<T> setData(T data) {
		this.data = data;
		return this;
	}
	public DataObj<T> setData(int code,T data) {
		this.code = code;
		this.data = data;
		return this;
	}
	public DataObj<T> setSuccessData(T data){
		this.code = CODE_SUCCESS;
		this.data = data;
		return this;
	}
	public DataObj<T> setSuccessData(int code, T data){
		this.code = code;
		this.successCode = code;
		this.data = data;
		return this;
	}
	
	public DataObj<T> setErrorMsg(String msg){
		this.code = CODE_FAIL;
		this.msg = msg;
		return this;
	}
	
	public DataObj<T> setErrorMsg(int code, String msg){
		this.code = code;
		this.msg = msg;
		return this;
	}
	
	public void setSuccessCode(int successCode) {
		this.successCode = successCode;
		this.code = successCode;
	}
	
	public String getMsg() {
		return msg;
	}
	public int getCode() {
		return code;
	}
	public T getData() {
		return data;
	}
}
