package com.zdy.biz.sysrolepermission.dto;

import com.zdy.biz.sysrolepermission.model.SysRolePermission;

public class CreateSysRolePermissionReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long sysRoleId;

	private Long sysPermissionId;

	private Long[] sysPermissionIds;

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

	public CreateSysRolePermissionReq() {

	}

	public CreateSysRolePermissionReq(SysRolePermission sysRolePermission) {
		if (sysRolePermission != null) {
			this.setSysRoleId(sysRolePermission.getSysRoleId());
			this.setSysPermissionId(sysRolePermission.getSysPermissionId());
			this.setSysPermissionIds(sysRolePermission.getSysPermissionIds());
		}
	}

	public SysRolePermission toSysRolePermission() {
		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setSysRoleId(this.sysRoleId);
		sysRolePermission.setSysPermissionId(this.sysPermissionId);
		sysRolePermission.setSysPermissionIds(this.sysPermissionIds);
		return sysRolePermission;
	}

	public Long[] getSysPermissionIds() {
		return sysPermissionIds;
	}

	public void setSysPermissionIds(Long[] sysPermissionIds) {
		this.sysPermissionIds = sysPermissionIds;
	}
}
