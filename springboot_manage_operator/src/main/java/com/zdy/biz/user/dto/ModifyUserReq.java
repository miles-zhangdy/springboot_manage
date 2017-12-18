package com.zdy.biz.user.dto;

import java.util.Date;

import com.zdy.biz.user.model.User;

public class ModifyUserReq {

	/**
	 * 
	 */
	@SuppressWarnings("unused")
	private static final long serialVersionUID = 1L;

	private Long id;
	private String userName;
	private String password;
	private Long parentId;
	private String userCompellation;
	private Integer userAge;
	private Integer userSex;
	private String userPhone;
	private Integer userFreeze;
	private Integer userValidate;
	private Date createTime;
	private String createUser;
	private Date modifyTime;

	public ModifyUserReq() {

	}

	public ModifyUserReq(User user) {
		if (null != user) {
			this.id = user.getId();
			this.userName = user.getUserName();
			this.password = user.getPassword();
			this.parentId = user.getParentId();
			this.userCompellation = user.getUserCompellation();
			this.userAge = user.getUserAge();
			this.userSex = user.getUserSex();
			this.userPhone = user.getUserPhone();
			this.userFreeze = user.getUserFreeze();
			this.userValidate = user.getUserValidate();
			this.createTime = user.getCreateTime();
			this.createUser = user.getCreateUser();
			this.modifyTime = user.getModifyTime();
		}
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	public Long getParentId() {
		return this.parentId;
	}

	public void setUserCompellation(String userCompellation) {
		this.userCompellation = userCompellation;
	}

	public String getUserCompellation() {
		return this.userCompellation;
	}

	public void setUserAge(Integer userAge) {
		this.userAge = userAge;
	}

	public Integer getUserAge() {
		return this.userAge;
	}

	public void setUserSex(Integer userSex) {
		this.userSex = userSex;
	}

	public Integer getUserSex() {
		return this.userSex;
	}

	public void setUserPhone(String userPhone) {
		this.userPhone = userPhone;
	}

	public String getUserPhone() {
		return this.userPhone;
	}

	public void setUserFreeze(Integer userFreeze) {
		this.userFreeze = userFreeze;
	}

	public Integer getUserFreeze() {
		return this.userFreeze;
	}

	public void setUserValidate(Integer userValidate) {
		this.userValidate = userValidate;
	}

	public Integer getUserValidate() {
		return this.userValidate;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateUser(String createUser) {
		this.createUser = createUser;
	}

	public String getCreateUser() {
		return this.createUser;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Date getModifyTime() {
		return this.modifyTime;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public User toUser() {
		User user = new User();
		user.setId(this.id);
		user.setUserName(this.userName);
		user.setPassword(this.password);
		user.setParentId(this.parentId);
		user.setUserCompellation(this.userCompellation);
		user.setUserAge(this.userAge);
		user.setUserSex(this.userSex);
		user.setUserPhone(this.userPhone);
		user.setUserFreeze(this.userFreeze);
		user.setUserValidate(this.userValidate);
		user.setCreateTime(this.createTime);
		user.setCreateUser(this.createUser);
		user.setModifyTime(this.modifyTime);
		return user;
	}
}
