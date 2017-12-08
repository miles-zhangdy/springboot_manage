package com.zdy.biz.dictionary.dto;

import java.util.Date;

import com.zdy.biz.dictionary.model.DictionaryParam;


public class CreateDictionaryParamReq{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	private Long dictionaryId;
	
	private String paramKey;
	
	private String paramDesc;
	
	private String remark;
	
	private Date createTime;
	
	private Long createUser;
	
	private Date operTime;
	
		public void setDictionaryId(Long dictionaryId) {
			this.dictionaryId = dictionaryId;
		}
		
		public Long getDictionaryId() {
			return this.dictionaryId;
		}
		public void setParamKey(String paramKey) {
			this.paramKey = paramKey;
		}
		
		public String getParamKey() {
			return this.paramKey;
		}
		public void setParamDesc(String paramDesc) {
			this.paramDesc = paramDesc;
		}
		
		public String getParamDesc() {
			return this.paramDesc;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
		
		public String getRemark() {
			return this.remark;
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
	
	public CreateDictionaryParamReq(){
		
	}
	
	public CreateDictionaryParamReq(DictionaryParam dictionaryParam){
		if(dictionaryParam != null){
				this.setDictionaryId(dictionaryParam.getDictionaryId());
				this.setParamKey(dictionaryParam.getParamKey());
				this.setParamDesc(dictionaryParam.getParamDesc());
				this.setRemark(dictionaryParam.getRemark());
				this.setCreateTime(dictionaryParam.getCreateTime());
				this.setCreateUser(dictionaryParam.getCreateUser());
				this.setOperTime(dictionaryParam.getOperTime());
		}
	}
	public DictionaryParam toDictionaryParam(){
		DictionaryParam  dictionaryParam = new DictionaryParam();
		dictionaryParam.setDictionaryId(this.dictionaryId);
		dictionaryParam.setParamKey(this.paramKey);
		dictionaryParam.setParamDesc(this.paramDesc);
		dictionaryParam.setRemark(this.remark);
		dictionaryParam.setCreateTime(this.createTime);
		dictionaryParam.setCreateUser(this.createUser);
		dictionaryParam.setOperTime(this.operTime);
		return dictionaryParam;
	}
}
