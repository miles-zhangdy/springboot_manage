package com.zdy.biz.syspermission.dto;

import java.util.Date;

public class SysPermissionListResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysPermissionResp[] list;
	
	public SysPermissionResp[] getList(){
		return list;
	}
	
	public void setList(SysPermissionResp[] list){
		this.list = list;
	}
}

