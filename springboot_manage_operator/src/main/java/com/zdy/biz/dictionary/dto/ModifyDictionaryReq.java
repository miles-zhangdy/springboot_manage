package com.zdy.biz.dictionary.dto;

import java.util.Date;

import com.zdy.biz.dictionary.model.Dictionary;

public class ModifyDictionaryReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Long id;
	private String dictionaryKey;
	private String dictionartDesc;
	private Date createTime;
	private Long createUser;
	private Date operTime;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return this.id;
	}

	public void setDictionaryKey(String dictionaryKey) {
		this.dictionaryKey = dictionaryKey;
	}

	public String getDictionaryKey() {
		return this.dictionaryKey;
	}

	public void setDictionartDesc(String dictionartDesc) {
		this.dictionartDesc = dictionartDesc;
	}

	public String getDictionartDesc() {
		return this.dictionartDesc;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getCreateUser() {
		return this.createUser;
	}

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Date getOperTime() {
		return this.operTime;
	}

	public ModifyDictionaryReq() {

	}

	public ModifyDictionaryReq(Dictionary dictionary) {
		if (dictionary != null) {
			this.setId(dictionary.getId());
			this.setDictionaryKey(dictionary.getDictionaryKey());
			this.setDictionartDesc(dictionary.getDictionartDesc());
			this.setCreateTime(dictionary.getCreateTime());
			this.setCreateUser(dictionary.getCreateUser());
			this.setOperTime(dictionary.getOperTime());
		}
	}

	public Dictionary toDictionary() {
		Dictionary dictionary = new Dictionary();
		dictionary.setId(this.id);
		dictionary.setDictionaryKey(this.dictionaryKey);
		dictionary.setDictionartDesc(this.dictionartDesc);
		dictionary.setCreateTime(this.createTime);
		dictionary.setCreateUser(this.createUser);
		dictionary.setOperTime(this.operTime);
		return dictionary;
	}

}
