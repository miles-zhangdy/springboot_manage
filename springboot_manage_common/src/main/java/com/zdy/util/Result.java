package com.zdy.util;

public class Result {
	private boolean success;// 操作是否成功

	private String msg;// 返回信息

	private Object data;// 返回数据

	public Result() {
	}

	public Result(boolean success) {
		this.success = success;
	}

	public Result(boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}

	public Result(boolean success, Object data) {
		this.success = success;
		this.data = data;
	}

	public Result(boolean success, Object data, String msg) {
		this.success = success;
		this.data = data;
		this.msg = msg;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}
}
