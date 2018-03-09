package com.wzxy.base.utils;

import java.io.Serializable;

public class JSONReturn implements Serializable {

	private static final long serialVersionUID = 1L;

	/*
	 * JSON头
	 */
	private boolean head;

	/*
	 * JSON主体
	 */
	private Object body;

	/*
	 * 返回头
	 */
	private static final boolean HEAD_SUCCESS = true;
	private static final boolean HEAD_FAILURE = false;

	/*
	 * 返回空主体
	 */
	private static final String EMPTY_BODY = "";

	public JSONReturn() {}

	public boolean isHead() {
		return head;
	}

	public void setHead(boolean head) {
		this.head = head;
	}

	public Object getBody() {
		return body;
	}

	public void setBody(Object body) {
		this.body = body;
	}

	public JSONReturn(boolean head, Object body) {
		super();
		this.head = head;
		this.body = body;
	}

	public static JSONReturn build(boolean head, Object body) {
		return new JSONReturn(head, body);
	}

	/*
	 * 成功
	 */
	public static JSONReturn buildSuccess(Object body) {
		return build(HEAD_SUCCESS, body);
	}

	/*
	 * 失败
	 */
	public static JSONReturn buildFailure(Object body) {
		return build(HEAD_FAILURE, body);
	}

	/*
	 * 成功,空主体
	 */
	public static JSONReturn buildSuccessWithEmptyBody() {
		return build(HEAD_SUCCESS, EMPTY_BODY);
	}

	/*
	 * 失败,空主体
	 */
	public static JSONReturn buildFailureWithEmptyBody() {
		return build(HEAD_FAILURE, EMPTY_BODY);
	}

}
