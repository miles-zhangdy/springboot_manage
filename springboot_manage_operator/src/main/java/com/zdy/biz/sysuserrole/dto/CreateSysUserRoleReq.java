package com.zdy.biz.sysuserrole.dto;

import com.zdy.biz.sysuserrole.model.SysUserRole;

public class CreateSysUserRoleReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sysUserId;

	private Long sysRoleId;
	
	private Long custId;
	
	private Long[] sysRoleIds;
	
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

	public CreateSysUserRoleReq() {

	}

	public CreateSysUserRoleReq(SysUserRole sysUserRole) {
		if (sysUserRole != null) {
			this.setSysUserId(sysUserRole.getSysUserId());
			this.setSysRoleId(sysUserRole.getSysRoleId());
			this.setCustId(sysUserRole.getCustId());
		}
	}

	public SysUserRole toSysUserRole() {
		SysUserRole sysUserRole = new SysUserRole();
		sysUserRole.setSysUserId(this.sysUserId);
		sysUserRole.setSysRoleId(this.sysRoleId);
		sysUserRole.setSysRoleIds(this.sysRoleIds);
		sysUserRole.setCustId(this.custId);
		return sysUserRole;
	}

	public Long[] getSysRoleIds() {
		return sysRoleIds;
	}

	public void setSysRoleIds(Long[] sysRoleIds) {
		this.sysRoleIds = sysRoleIds;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}
}
