package com.zdy.util;

public class ServiceResult<B> {

	private boolean isSuccess;

	private String msg;

	private B businessObject;

	public boolean isSuccess() {
		return isSuccess;
	}

	public void setSuccess(boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public B getBusinessObject() {
		return businessObject;
	}

	public void setBusinessObject(B businessObject) {
		this.businessObject = businessObject;
	}

	public ServiceResult<B> error(String msg){
		this.isSuccess = false;
		this.msg = msg;
		return this;
	}
	
	public ServiceResult<B> success(B b){
		this.isSuccess = true;
		this.businessObject = b;
		this.msg = "操作成功";
		return this;
	}
	
	public ServiceResult<B> success(){
		this.isSuccess = true;
		this.msg = "操作成功";
		return this;
	}
	
}
