package com.zdy.biz.sysuserrole.dto;

import com.zdy.biz.sysuserrole.model.SysUserRole;


public class ModifySysUserRoleReq  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private Long sysUserId;
	private Long sysRoleId;
	
	public void setId(Long id) {
		this.id = id;
	}
	
	public Long getId() {
		return this.id;
	}
	public void setSysUserId(Long sysUserId) {
		this.sysUserId = sysUserId;
	}
	
	public Long getSysUserId() {
		return this.sysUserId;
	}
	public void setSysRoleId(Long sysRoleId) {
		this.sysRoleId = sysRoleId;
	}
	
	public Long getSysRoleId() {
		return this.sysRoleId;
	}

	public ModifySysUserRoleReq(){
		
	}
	
	public ModifySysUserRoleReq(SysUserRole sysUserRole){
		if(sysUserRole != null){
			this.setId(sysUserRole.getId());
			this.setSysUserId(sysUserRole.getSysUserId());
			this.setSysRoleId(sysUserRole.getSysRoleId());
		}
	}
	public SysUserRole toSysUserRole(){
		SysUserRole  sysUserRole = new SysUserRole();
		sysUserRole.setId(this.id);
		sysUserRole.setSysUserId(this.sysUserId);
		sysUserRole.setSysRoleId(this.sysRoleId);
		return sysUserRole;
	}
	
}
