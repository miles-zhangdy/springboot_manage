package com.zdy.biz.sysrolepermission.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.util.Page;


public class SysRolePermission extends Page implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * 角色id       db_column: sys_role_id 
     */	
	private Long sysRoleId;
    /**
     * 权限id       db_column: sys_permission_id 
     */	
	private Long sysPermissionId;

	private Long[] sysPermissionIds;
	
	private Long[] ids;
	
	public SysRolePermission(){
	}

	public SysRolePermission(Long id){
		this.id = id;
	}


	public SysRolePermission(SysRolePermission sysRolePermission){
		if(null != sysRolePermission){
				this.setId(sysRolePermission.getId());
				this.setSysRoleId(sysRolePermission.getSysRoleId());
				this.setSysPermissionId(sysRolePermission.getSysPermissionId());
		}
	}
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("sysRoleId",this.sysRoleId);
		map.put("sysPermissionId",this.sysPermissionId);
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

	public Long[] getSysPermissionIds() {
		return sysPermissionIds;
	}

	public void setSysPermissionIds(Long[] sysPermissionIds) {
		this.sysPermissionIds = sysPermissionIds;
	}

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
 
}

