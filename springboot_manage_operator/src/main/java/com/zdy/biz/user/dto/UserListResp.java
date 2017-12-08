package com.zdy.biz.user.dto;

public class UserListResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private UserResp[] list;
	
	public UserResp[] getList(){
		return list;
	}
	
	public void setList(UserResp[] list){
		this.list = list;
	}
}

