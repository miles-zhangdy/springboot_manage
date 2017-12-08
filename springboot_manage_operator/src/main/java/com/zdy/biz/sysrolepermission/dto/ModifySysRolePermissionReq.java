package com.zdy.biz.sysrolepermission.dto;

import com.zdy.biz.sysrolepermission.model.SysRolePermission;


public class ModifySysRolePermissionReq  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long sysRoleId;
	private Long sysPermissionId;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	public Long getSysRoleId() {
		return this.sysRoleId;
	}
	public void setSysPermissionId(Long sysPermissionId) {
		this.sysPermissionId = sysPermissionId;
	}
	
	public Long getSysPermissionId() {
		return this.sysPermissionId;
	}

	public ModifySysRolePermissionReq(){
		
	}
	
	public ModifySysRolePermissionReq(SysRolePermission sysRolePermission){
		if(sysRolePermission != null){
			this.setId(sysRolePermission.getId());
			this.setSysRoleId(sysRolePermission.getSysRoleId());
			this.setSysPermissionId(sysRolePermission.getSysPermissionId());
		}
	}
	public SysRolePermission toSysRolePermission(){
		SysRolePermission  sysRolePermission = new SysRolePermission();
		sysRolePermission.setId(this.id);
		sysRolePermission.setSysRoleId(this.sysRoleId);
		sysRolePermission.setSysPermissionId(this.sysPermissionId);
		return sysRolePermission;
	}
	
}
