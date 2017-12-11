package com.zdy.common.model;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.zdy.biz.syspermission.dto.SysPermissionResp;

import net.sf.json.JSONArray;

public class SessionUser implements java.io.Serializable {

	private static final long serialVersionUID = 1L;

	/**
	 * id db_column: id
	 */
	private Long id;

	/**
	 * userName db_column: user_name
	 */
	private String userName;
	/**
	 * password db_column: password
	 */
	private String password;
	/**
	 * 父级id 0：主账户 非0子账户 db_column: parent_id
	 */
	private Long parentId;
	/**
	 * 真实姓名 db_column: user_compellation
	 */
	private String userCompellation;
	/**
	 * userAge db_column: user_age
	 */
	private Integer userAge;
	/**
	 * 0女1男 db_column: user_sex
	 */
	private Integer userSex;
	/**
	 * userPhone db_column: user_phone
	 */
	private String userPhone;
	/**
	 * 0不冻结1冻结 db_column: user_freeze
	 */
	private Integer userFreeze;
	/**
	 * 0未审核1审核 db_column: user_validate
	 */
	private Integer userValidate;
	/**
	 * createTime db_column: create_time
	 */
	private Date createTime;
	/**
	 * createUser db_column: create_user
	 */
	private String createUser;
	/**
	 * modifyTime db_column: modify_time
	 */
	private Date modifyTime;

	private String permissionUrl;
	
	private List<SysPermissionResp> permissionList;
	
	private JSONArray urls;//登录用户所能访问的url
	
	private Long[] roleIds;
	
	private Long custId;
	
	public SessionUser() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public SessionUser(Long id) {
		this.id = id;
	}

	public Map<String, Object> toMap() {
		HashMap<String, Object> map = new HashMap<>();
		map.put("id", this.id);
		map.put("userName", this.userName);
		map.put("password", this.password);
		map.put("parentId", this.parentId);
		map.put("userCompellation", this.userCompellation);
		map.put("userAge", this.userAge);
		map.put("userSex", this.userSex);
		map.put("userPhone", this.userPhone);
		map.put("userFreeze", this.userFreeze);
		map.put("userValidate", this.userValidate);
		map.put("createTime", this.createTime);
		map.put("createUser", this.createUser);
		map.put("modifyTime", this.modifyTime);
		return map;
	}

	public void setUserName(String value) {
		this.userName = value;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String value) {
		this.password = value;
	}

	public String getPassword() {
		return this.password;
	}

	public void setParentId(Long value) {
		this.parentId = value;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setUserCompellation(String value) {
		this.userCompellation = value;
	}

	public String getUserCompellation() {
		return this.userCompellation;
	}

	public void setUserAge(Integer value) {
		this.userAge = value;
	}

	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserSex(Integer value) {
		this.userSex = value;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserPhone(String value) {
		this.userPhone = value;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserFreeze(Integer value) {
		this.userFreeze = value;
	}

	public Integer getUserFreeze() {
		return this.userFreeze;
	}

	public void setUserValidate(Integer value) {
		this.userValidate = value;
	}

	public Integer getUserValidate() {
		return this.userValidate;
	}

	public void setCreateTime(Date value) {
		this.createTime = value;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateUser(String value) {
		this.createUser = value;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setModifyTime(Date value) {
		this.modifyTime = value;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public String getPermissionUrl() {
		return permissionUrl;
	}

	public void setPermissionUrl(String permissionUrl) {
		this.permissionUrl = permissionUrl;
	}

	public  JSONArray	 getUrls() {
		return urls;
	}

	public void setUrls(JSONArray urls) {
		this.urls = urls;
	}

	public Long[] getRoleIds() {
		return roleIds;
	}

	public void setRoleIds(Long[] roleIds) {
		this.roleIds = roleIds;
	}

	public List<SysPermissionResp> getPermissionList() {
		return permissionList;
	}

	public void setPermissionList(List<SysPermissionResp> permissionList) {
		this.permissionList = permissionList;
	}

	public Long getCustId() {
		return custId;
	}

	public void setCustId(Long custId) {
		this.custId = custId;
	}

}
