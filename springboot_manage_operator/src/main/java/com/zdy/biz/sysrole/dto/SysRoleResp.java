package com.zdy.biz.sysrole.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.sysrole.model.SysRole;

public class SysRoleResp {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * name       db_column: name 
     */	
	private String name;
    /**
     * 是否可用,1：可用，0不可用       db_column: available 
     */	
	private String available;

	private String checked;
	
	public SysRoleResp(){
		
	}
	
	public SysRoleResp(SysRoleResp sysRoleResp){
		if(null != sysRoleResp){
				this.setId(sysRoleResp.getId());
				this.setName(sysRoleResp.getName());
				this.setAvailable(sysRoleResp.getAvailable());
		}
	}
	
	public SysRoleResp(SysRole sysRole){
		if(null != sysRole){
				this.id = sysRole.getId();
				this.name = sysRole.getName();
				this.available = sysRole.getAvailable();
				this.setChecked(sysRole.getChecked());
		}
	}
	
	public SysRole toSysRole(){
		SysRole  sysRole = new SysRole();
		sysRole.setId(this.id);
		sysRole.setName(this.name);
		sysRole.setAvailable(this.available);
		return sysRole;
	}
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("name",this.name);
		map.put("available",this.available);
		return map;
	}
	
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
		
	public void setName(String value) {
		this.name = value;
	}
	
	public String getName() {
		return this.name;
	}
		
	public void setAvailable(String value) {
		this.available = value;
	}
	
	public String getAvailable() {
		return this.available;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}

 
}

