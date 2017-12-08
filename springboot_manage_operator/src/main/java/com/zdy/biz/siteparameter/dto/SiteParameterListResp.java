package com.zdy.biz.siteparameter.dto;

import java.io.Serializable;
import java.util.Date;

public class SiteParameterListResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private SiteParameterResp[] list;
	
	public SiteParameterResp[] getList(){
		return list;
	}
	
	public void setList(SiteParameterResp[] list){
		this.list = list;
	}
}

