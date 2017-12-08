package com.zdy.biz.dictionary.dto;

import java.io.Serializable;
import java.util.Date;

public class DictionaryParamListResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DictionaryParamResp[] list;
	
	public DictionaryParamResp[] getList(){
		return list;
	}
	
	public void setList(DictionaryParamResp[] list){
		this.list = list;
	}
}

