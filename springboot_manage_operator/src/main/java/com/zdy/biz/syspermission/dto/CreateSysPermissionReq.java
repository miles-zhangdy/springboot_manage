package com.zdy.biz.syspermission.dto;

import com.zdy.biz.syspermission.model.SysPermission;

public class CreateSysPermissionReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String name;

	private String type;

	private String url;

	private String percode;

	private Long parentid;

	private String sortstring;

	private String available;
	
	private String className;
	
	private String isShow;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getType() {
		return this.type;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getUrl() {
		return this.url;
	}

	public void setPercode(String percode) {
		this.percode = percode;
	}

	public String getPercode() {
		return this.percode;
	}

	public void setParentid(Long parentid) {
		this.parentid = parentid;
	}

	public Long getParentid() {
		return this.parentid;
	}

	public void setSortstring(String sortstring) {
		this.sortstring = sortstring;
	}

	public String getSortstring() {
		return this.sortstring;
	}

	public void setAvailable(String available) {
		this.available = available;
	}

	public String getAvailable() {
		return this.available;
	}

	public CreateSysPermissionReq() {

	}

	public CreateSysPermissionReq(SysPermission sysPermission) {
		if (sysPermission != null) {
			this.setName(sysPermission.getName());
			this.setType(sysPermission.getType());
			this.setUrl(sysPermission.getUrl());
			this.setPercode(sysPermission.getPercode());
			this.setParentid(sysPermission.getParentid());
			this.setSortstring(sysPermission.getSortstring());
			this.setAvailable(sysPermission.getAvailable());
			this.setClassName(sysPermission.getClassName());
			this.setIsShow(sysPermission.getIsShow());
		}
	}

	public SysPermission toSysPermission() {
		SysPermission sysPermission = new SysPermission();
		sysPermission.setName(this.name);
		sysPermission.setType(this.type);
		sysPermission.setUrl(this.url);
		sysPermission.setPercode(this.percode);
		sysPermission.setParentid(this.parentid);
		sysPermission.setSortstring(this.sortstring);
		sysPermission.setAvailable(this.available);
		sysPermission.setClassName(this.className);
		sysPermission.setIsShow(this.isShow);
		return sysPermission;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public String getIsShow() {
		return isShow;
	}

	public void setIsShow(String isShow) {
		this.isShow = isShow;
	}
}
