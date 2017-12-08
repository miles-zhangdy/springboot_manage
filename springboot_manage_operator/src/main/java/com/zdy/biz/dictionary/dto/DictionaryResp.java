package com.zdy.biz.dictionary.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.dictionary.model.Dictionary;

public class DictionaryResp implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * dictionaryKey       db_column: dictionary_key 
     */	
	private String dictionaryKey;
    /**
     * dictionartDesc       db_column: dictionart_desc 
     */	
	private String dictionartDesc;
    /**
     * createTime       db_column: create_time 
     */	
	private Date createTime;
    /**
     * createUser       db_column: create_user 
     */	
	private Long createUser;
    /**
     * operTime       db_column: oper_time 
     */	
	private Date operTime;

	public DictionaryResp(){
		
	}
	
	public DictionaryResp(DictionaryResp dictionaryResp){
		if(null != dictionaryResp){
				this.setId(dictionaryResp.getId());
				this.setDictionaryKey(dictionaryResp.getDictionaryKey());
				this.setDictionartDesc(dictionaryResp.getDictionartDesc());
				this.setCreateTime(dictionaryResp.getCreateTime());
				this.setCreateUser(dictionaryResp.getCreateUser());
				this.setOperTime(dictionaryResp.getOperTime());
		}
	}
	
	public DictionaryResp(Dictionary dictionary){
		if(null != dictionary){
				this.id = dictionary.getId();
				this.dictionaryKey = dictionary.getDictionaryKey();
				this.dictionartDesc = dictionary.getDictionartDesc();
				this.createTime = dictionary.getCreateTime();
				this.createUser = dictionary.getCreateUser();
				this.operTime = dictionary.getOperTime();
		}
	}
	
	public Dictionary toDictionary(){
		Dictionary  dictionary = new Dictionary();
		dictionary.setId(this.id);
		dictionary.setDictionaryKey(this.dictionaryKey);
		dictionary.setDictionartDesc(this.dictionartDesc);
		dictionary.setCreateTime(this.createTime);
		dictionary.setCreateUser(this.createUser);
		dictionary.setOperTime(this.operTime);
		return dictionary;
	}
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("dictionaryKey",this.dictionaryKey);
		map.put("dictionartDesc",this.dictionartDesc);
		map.put("createTime",this.createTime);
		map.put("createUser",this.createUser);
		map.put("operTime",this.operTime);
		return map;
	}
	
		
	public void setId(Long value) {
		this.id = value;
	}
	
	public Long getId() {
		return this.id;
	}
		
	public void setDictionaryKey(String value) {
		this.dictionaryKey = value;
	}
	
	public String getDictionaryKey() {
		return this.dictionaryKey;
	}
		
	public void setDictionartDesc(String value) {
		this.dictionartDesc = value;
	}
	
	public String getDictionartDesc() {
		return this.dictionartDesc;
	}
		
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	
	public Date getCreateTime() {
		return this.createTime;
	}
		
	public void setCreateUser(Long value) {
		this.createUser = value;
	}
	
	public Long getCreateUser() {
		return this.createUser;
	}
		
	public void setOperTime(Date value) {
		this.operTime = value;
	}
	
	public Date getOperTime() {
		return this.operTime;
	}

 
}

