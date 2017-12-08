package com.zdy.biz.sysrole.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.util.Page;


public class SysRole extends Page implements java.io.Serializable {
	
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

	private Long userId;
	
	private Long[] ids;
	
	private String checked;
	
	public SysRole(){
	}

	public SysRole(Long id){
		this.id = id;
	}


	public SysRole(SysRole sysRole){
		if(null != sysRole){
				this.setId(sysRole.getId());
				this.setName(sysRole.getName());
				this.setAvailable(sysRole.getAvailable());
		}
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

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getChecked() {
		return checked;
	}

	public void setChecked(String checked) {
		this.checked = checked;
	}
 
}

