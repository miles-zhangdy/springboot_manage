package com.zdy.biz.sysrolepermission.dto;

public class SysRolePermissionListResp  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SysRolePermissionResp[] list;
	
	public SysRolePermissionResp[] getList(){
		return list;
	}
	
	public void setList(SysRolePermissionResp[] list){
		this.list = list;
	}
}

