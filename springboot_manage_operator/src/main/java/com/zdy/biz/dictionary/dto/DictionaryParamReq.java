package com.zdy.biz.dictionary.dto;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.zdy.biz.dictionary.model.DictionaryParam;
import com.zdy.util.Page;


public class DictionaryParamReq extends Page {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
    /**
     * id       db_column: id 
     */	
	private Long id;
    /**
     * dictionaryId       db_column: dictionary_id 
     */	
	private Long dictionaryId;
    /**
     * paramKey       db_column: param_key 
     */	
	private String paramKey;
    /**
     * paramDesc       db_column: param_desc 
     */	
	private String paramDesc;
    /**
     * remark       db_column: remark 
     */	
	private String remark;
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
	
	private Long[] ids;
	
	public void setIds(Long[] ids){
		this.ids = ids;
	}
	
	public Long[] getIds(){
		return this.ids;
	}
	
	public DictionaryParamReq(){
		
	}
	
	public DictionaryParamReq(DictionaryParamReq dictionaryParamReq){
		if(null != dictionaryParamReq){
				this.setId(dictionaryParamReq.getId());
				this.setDictionaryId(dictionaryParamReq.getDictionaryId());
				this.setParamKey(dictionaryParamReq.getParamKey());
				this.setParamDesc(dictionaryParamReq.getParamDesc());
				this.setRemark(dictionaryParamReq.getRemark());
				this.setCreateTime(dictionaryParamReq.getCreateTime());
				this.setCreateUser(dictionaryParamReq.getCreateUser());
				this.setOperTime(dictionaryParamReq.getOperTime());
			this.setSortName(dictionaryParamReq.getSortName());
			this.setOrder(dictionaryParamReq.getOrder());
		}
	}
	
	public DictionaryParamReq(DictionaryParam dictionaryParam){
		if(null != dictionaryParam){
				this.id = dictionaryParam.getId();
				this.dictionaryId = dictionaryParam.getDictionaryId();
				this.paramKey = dictionaryParam.getParamKey();
				this.paramDesc = dictionaryParam.getParamDesc();
				this.remark = dictionaryParam.getRemark();
				this.createTime = dictionaryParam.getCreateTime();
				this.createUser = dictionaryParam.getCreateUser();
				this.operTime = dictionaryParam.getOperTime();
		}
	}
	
	public DictionaryParam toDictionaryParam(){
		DictionaryParam  dictionaryParam = new DictionaryParam();
		dictionaryParam.setId(this.id);
		dictionaryParam.setDictionaryId(this.dictionaryId);
		dictionaryParam.setParamKey(this.paramKey);
		dictionaryParam.setParamDesc(this.paramDesc);
		dictionaryParam.setRemark(this.remark);
		dictionaryParam.setCreateTime(this.createTime);
		dictionaryParam.setCreateUser(this.createUser);
		dictionaryParam.setOperTime(this.operTime);
		dictionaryParam.setIds(this.ids);
		return dictionaryParam;
	}
	
	public Map<String,Object> toMap(){
		HashMap<String, Object> map = new HashMap<>();
		map.put("id",this.id);
		map.put("dictionaryId",this.dictionaryId);
		map.put("paramKey",this.paramKey);
		map.put("paramDesc",this.paramDesc);
		map.put("remark",this.remark);
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
	
		
	public void setDictionaryId(Long value) {
		this.dictionaryId = value;
	}
	
	public Long getDictionaryId() {
		return this.dictionaryId;
	}
	
		
	public void setParamKey(String value) {
		this.paramKey = value;
	}
	
	public String getParamKey() {
		return this.paramKey;
	}
	
		
	public void setParamDesc(String value) {
		this.paramDesc = value;
	}
	
	public String getParamDesc() {
		return this.paramDesc;
	}
	
		
	public void setRemark(String value) {
		this.remark = value;
	}
	
	public String getRemark() {
		return this.remark;
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

