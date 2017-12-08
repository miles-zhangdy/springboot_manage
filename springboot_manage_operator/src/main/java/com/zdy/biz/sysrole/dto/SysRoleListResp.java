package com.zdy.biz.sysrole.dto;

import java.util.Date;

public class SysRoleListResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysRoleResp[] list;
	
	public SysRoleResp[] getList(){
		return list;
	}
	
	public void setList(SysRoleResp[] list){
		this.list = list;
	}
}

