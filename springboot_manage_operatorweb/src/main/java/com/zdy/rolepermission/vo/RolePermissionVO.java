package com.zdy.rolepermission.vo;

import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.sysrolepermission.model.SysRolePermission;
import com.zdy.util.Page;


public class RolePermissionVO extends Page implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id db_column: id
	 */
	private Long id;
	/**
	 * 角色id db_column: sys_role_id
	 */
	private Long sysRoleId;
	/**
	 * 权限id db_column: sys_permission_id
	 */
	private Long sysPermissionId;
	

	public RolePermissionVO() {
	}

	public RolePermissionVO(Long id) {
		this.id = id;
	}

	public RolePermissionVO(RolePermissionVO sysRolePermission) {
		if (null != sysRolePermission) {
			this.setId(sysRolePermission.getId());
			this.setSysRoleId(sysRolePermission.getSysRoleId());
			this.setSysPermissionId(sysRolePermission.getSysPermissionId());
		}
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", this.id);
		map.put("sysRoleId", this.sysRoleId);
		map.put("sysPermissionId", this.sysPermissionId);
		return map;
	}

	public void setId(Long value) {
		this.id = value;
	}

	public Long getId() {
		return this.id;
	}

	public void setSysRoleId(Long value) {
		this.sysRoleId = value;
	}

	public Long getSysRoleId() {
		return this.sysRoleId;
	}

	public void setSysPermissionId(Long value) {
		this.sysPermissionId = value;
	}

	public Long getSysPermissionId() {
		return this.sysPermissionId;
	}

	public SysRolePermission toSysRolePermission() {
		SysRolePermission sysRolePermission = new SysRolePermission();
		sysRolePermission.setId(this.id);
		sysRolePermission.setSysRoleId(this.sysRoleId);
		sysRolePermission.setSysPermissionId(this.sysPermissionId);
		return sysRolePermission;
	}

}
