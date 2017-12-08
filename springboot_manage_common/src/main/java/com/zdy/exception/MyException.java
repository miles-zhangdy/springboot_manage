package com.zdy.exception;

public class MyException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -888031393942673792L;
	private String errMsg = null;

	@Override
	public String getMessage() {
		return errMsg;
	}

	public MyException(String msg, Exception ex) {
		initCause(ex);
		errMsg = msg + ex.toString();
		ex.printStackTrace();
	}

	public MyException(String msg) {
		setErrMsg(msg);
	}

	public String getErrMsg() {
		return errMsg;
	}

	public void setErrMsg(String errMsg) {
		this.errMsg = errMsg;
	}

	public String toString() {
		return errMsg;
	}
}
