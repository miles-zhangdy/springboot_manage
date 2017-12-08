package com.zdy.biz.sysuserrole.dto;

import java.util.Date;

public class SysUserRoleListResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysUserRoleResp[] list;
	
	public SysUserRoleResp[] getList(){
		return list;
	}
	
	public void setList(SysUserRoleResp[] list){
		this.list = list;
	}
}

