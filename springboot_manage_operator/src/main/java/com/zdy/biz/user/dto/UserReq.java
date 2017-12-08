package com.zdy.biz.user.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.user.model.User;
import com.zdy.util.Page;


public class UserReq extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * userName       db_column: user_name 
     */	
	private String userName;
    /**
     * password       db_column: password 
     */	
	private String password;
    /**
     * 父级id  0：主账户 非0子账户       db_column: parent_id 
     */	
	private Long parentId;
    /**
     * 真实姓名       db_column: user_compellation 
     */	
	private String userCompellation;
    /**
     * userAge       db_column: user_age 
     */	
	private Integer userAge;
    /**
     * 0女1男       db_column: user_sex 
     */	
	private Integer userSex;
    /**
     * userPhone       db_column: user_phone 
     */	
	private String userPhone;
    /**
     * 0不冻结1冻结       db_column: user_freeze 
     */	
	private Integer userFreeze;
    /**
     * 0未审核1审核       db_column: user_validate 
     */	
	private Integer userValidate;
    /**
     * createTime       db_column: create_time 
     */	
	private Date createTime;
    /**
     * createUser       db_column: create_user 
     */	
	private String createUser;
    /**
     * modifyTime       db_column: modify_time 
     */	
	private Date modifyTime;
	
	private Long[] ids;
	
	public UserReq(){
		
	}
	
	public UserReq(UserReq userReq){
		if(null != userReq){
				this.setId(userReq.getId());
				this.setUserName(userReq.getUserName());
				this.setPassword(userReq.getPassword());
				this.setParentId(userReq.getParentId());
				this.setUserCompellation(userReq.getUserCompellation());
				this.setUserAge(userReq.getUserAge());
				this.setUserSex(userReq.getUserSex());
				this.setUserPhone(userReq.getUserPhone());
				this.setUserFreeze(userReq.getUserFreeze());
				this.setUserValidate(userReq.getUserValidate());
				this.setCreateTime(userReq.getCreateTime());
				this.setMaxCreateTime(userReq.getMaxCreateTime());
				this.setMinCreateTime(userReq.getMinCreateTime());
				this.setCreateUser(userReq.getCreateUser());
				this.setModifyTime(userReq.getModifyTime());
				this.setMaxModifyTime(userReq.getMaxModifyTime());
				this.setMinModifyTime(userReq.getMinModifyTime());
		}
	}
	
	public UserReq(User user){
		if(null != user){
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
	
	public User toUser(){
		User  user = new User();
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
		user.setIds(this.ids);
		user.setPage(this.getPage());
		user.setBeginIndex(this.getBeginIndex());
		user.setPageSize(this.getPageSize());
		return user;
	}
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("userName",this.userName);
		map.put("password",this.password);
		map.put("parentId",this.parentId);
		map.put("userCompellation",this.userCompellation);
		map.put("userAge",this.userAge);
		map.put("userSex",this.userSex);
		map.put("userPhone",this.userPhone);
		map.put("userFreeze",this.userFreeze);
		map.put("userValidate",this.userValidate);
		map.put("createTime",this.createTime);
		map.put("maxCreateTime",this.maxCreateTime);
		map.put("minCreateTime",this.minCreateTime);
		map.put("createUser",this.createUser);
		map.put("modifyTime",this.modifyTime);
		map.put("maxModifyTime",this.maxModifyTime);
		map.put("minModifyTime",this.minModifyTime);
		return map;
	}
	
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
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
	
    private Date minCreateTime;
    private Date maxCreateTime;
    
    public void setMinCreateTime(Date value) {
		this.minCreateTime = value;
	}
    public Date getMinCreateTime() {
		return this.minCreateTime;
	}
    public void setMaxCreateTime(Date value) {
    	this.maxCreateTime = value;
    }
    public Date getMaxCreateTime() {
    	return this.maxCreateTime;
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
	
    private Date minModifyTime;
    private Date maxModifyTime;
    
    public void setMinModifyTime(Date value) {
		this.minModifyTime = value;
	}
    public Date getMinModifyTime() {
		return this.minModifyTime;
	}
    public void setMaxModifyTime(Date value) {
    	this.maxModifyTime = value;
    }
    public Date getMaxModifyTime() {
    	return this.maxModifyTime;
    }

	public Long[] getIds() {
		return ids;
	}

	public void setIds(Long[] ids) {
		this.ids = ids;
	}
	

 
	
 
}

