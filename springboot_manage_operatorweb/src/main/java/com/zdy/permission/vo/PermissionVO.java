package com.zdy.permission.vo;

import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.syspermission.model.SysPermission;
import com.zdy.util.Page;



public class PermissionVO extends Page implements java.io.Serializable {
	
	private static final long serialVersionUID = 1L;
	
    /**
     * 主键       db_column: id 
     */	
	private Long id;
    /**
     * 资源名称       db_column: name 
     */	
	private String name;
    /**
     * 资源类型：menu,button,       db_column: type 
     */	
	private String type;
    /**
     * 访问url地址       db_column: url 
     */	
	private String url;
    /**
     * 权限代码字符串       db_column: percode 
     */	
	private String percode;
    /**
     * 父结点id       db_column: parentid 
     */	
	private Long parentid;
    /**
     * 排序号       db_column: sortstring 
     */	
	private String sortstring;
    /**
     * 是否可用,1：可用，0不可用       db_column: available 
     */	
	private String available;

	private String isShow;
	
	/**
	 * 菜单图标
	 */
	private String menuClassName;
	
	public PermissionVO(){
	}

	public PermissionVO(Long id){
		this.id = id;
	}


	public PermissionVO(PermissionVO sysPermission){
		if(null != sysPermission){
				this.setId(sysPermission.getId());
				this.setName(sysPermission.getName());
				this.setType(sysPermission.getType());
				this.setUrl(sysPermission.getUrl());
				this.setPercode(sysPermission.getPercode());
				this.setParentid(sysPermission.getParentid());
				this.setSortstring(sysPermission.getSortstring());
				this.setAvailable(sysPermission.getAvailable());
				this.setMenuClassName(sysPermission.getMenuClassName());
				this.setIsShow(sysPermission.getIsShow());
		}
	}
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("name",this.name);
		map.put("type",this.type);
		map.put("url",this.url);
		map.put("percode",this.percode);
		map.put("parentid",this.parentid);
		map.put("sortstring",this.sortstring);
		map.put("available",this.available);
		map.put("className", this.menuClassName);
		map.put("isShow", this.isShow);
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
		
	public void setType(String value) {
		this.type = value;
	}
	
	public String getType() {
		return this.type;
	}
		
	public void setUrl(String value) {
		this.url = value;
	}
	
	public String getUrl() {
		return this.url;
	}
		
	public void setPercode(String value) {
		this.percode = value;
	}
	
	public String getPercode() {
		return this.percode;
	}
		
	public void setParentid(Long value) {
		this.parentid = value;
	}
	
	public Long getParentid() {
		return this.parentid;
	}
		
	public void setSortstring(String value) {
		this.sortstring = value;
	}
	
	public String getSortstring() {
		return this.sortstring;
	}
		
	public void setAvailable(String value) {
		this.available = value;
	}
	
	public String getAvailable() {
		return this.available;
	}
 
	public SysPermission toSysPermission(){
		SysPermission  sysPermission = new SysPermission();
		sysPermission.setId(this.id);
		sysPermission.setName(this.name);
		sysPermission.setType(this.type);
		sysPermission.setUrl(this.url);
		sysPermission.setPercode(this.percode);
		sysPermission.setParentid(this.parentid);
		sysPermission.setSortstring(this.sortstring);
		sysPermission.setAvailable(this.available);
		sysPermission.setClassName(this.menuClassName);
		sysPermission.setIsShow(this.isShow);
		return sysPermission;
	}

	public String getMenuClassName() {
		return menuClassName;
	}

	public void setMenuClassName(String menuClassName) {
		this.menuClassName = menuClassName;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}

}

