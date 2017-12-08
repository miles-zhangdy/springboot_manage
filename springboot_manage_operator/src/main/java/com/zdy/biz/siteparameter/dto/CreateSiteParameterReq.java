package com.zdy.biz.siteparameter.dto;

import java.util.Date;

import com.zdy.biz.siteparameter.model.SiteParameter;

public class CreateSiteParameterReq {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String paramName;

	private String paramValue;

	private String paramComment;

	private String remark;

	private Date createTime;

	private Date operTime;

	private Long createUser;

	private String rsrvStr1;

	private String rsrvStr2;

	private String rsrvStr3;

	private String rsrvStr4;

	private String rsrvStr5;

	private String rsrvStr6;

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamName() {
		return this.paramName;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public String getParamValue() {
		return this.paramValue;
	}

	public void setParamComment(String paramComment) {
		this.paramComment = paramComment;
	}

	public String getParamComment() {
		return this.paramComment;
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

	public void setOperTime(Date operTime) {
		this.operTime = operTime;
	}

	public Date getOperTime() {
		return this.operTime;
	}

	public void setCreateUser(Long createUser) {
		this.createUser = createUser;
	}

	public Long getCreateUser() {
		return this.createUser;
	}

	public void setRsrvStr1(String rsrvStr1) {
		this.rsrvStr1 = rsrvStr1;
	}

	public String getRsrvStr1() {
		return this.rsrvStr1;
	}

	public void setRsrvStr2(String rsrvStr2) {
		this.rsrvStr2 = rsrvStr2;
	}

	public String getRsrvStr2() {
		return this.rsrvStr2;
	}

	public void setRsrvStr3(String rsrvStr3) {
		this.rsrvStr3 = rsrvStr3;
	}

	public String getRsrvStr3() {
		return this.rsrvStr3;
	}

	public void setRsrvStr4(String rsrvStr4) {
		this.rsrvStr4 = rsrvStr4;
	}

	public String getRsrvStr4() {
		return this.rsrvStr4;
	}

	public void setRsrvStr5(String rsrvStr5) {
		this.rsrvStr5 = rsrvStr5;
	}

	public String getRsrvStr5() {
		return this.rsrvStr5;
	}

	public void setRsrvStr6(String rsrvStr6) {
		this.rsrvStr6 = rsrvStr6;
	}

	public String getRsrvStr6() {
		return this.rsrvStr6;
	}

	public CreateSiteParameterReq() {

	}

	public CreateSiteParameterReq(SiteParameter siteParameter) {
		if (siteParameter != null) {
			this.setParamName(siteParameter.getParamName());
			this.setParamValue(siteParameter.getParamValue());
			this.setParamComment(siteParameter.getParamComment());
			this.setRemark(siteParameter.getRemark());
			this.setCreateTime(siteParameter.getCreateTime());
			this.setOperTime(siteParameter.getOperTime());
			this.setCreateUser(siteParameter.getCreateUser());
			this.setRsrvStr1(siteParameter.getRsrvStr1());
			this.setRsrvStr2(siteParameter.getRsrvStr2());
			this.setRsrvStr3(siteParameter.getRsrvStr3());
			this.setRsrvStr4(siteParameter.getRsrvStr4());
			this.setRsrvStr5(siteParameter.getRsrvStr5());
			this.setRsrvStr6(siteParameter.getRsrvStr6());
		}
	}

	public SiteParameter toSiteParameter() {
		SiteParameter siteParameter = new SiteParameter();
		siteParameter.setParamName(this.paramName);
		siteParameter.setParamValue(this.paramValue);
		siteParameter.setParamComment(this.paramComment);
		siteParameter.setRemark(this.remark);
		siteParameter.setCreateTime(this.createTime);
		siteParameter.setOperTime(this.operTime);
		siteParameter.setCreateUser(this.createUser);
		siteParameter.setRsrvStr1(this.rsrvStr1);
		siteParameter.setRsrvStr2(this.rsrvStr2);
		siteParameter.setRsrvStr3(this.rsrvStr3);
		siteParameter.setRsrvStr4(this.rsrvStr4);
		siteParameter.setRsrvStr5(this.rsrvStr5);
		siteParameter.setRsrvStr6(this.rsrvStr6);
		return siteParameter;
	}
}
