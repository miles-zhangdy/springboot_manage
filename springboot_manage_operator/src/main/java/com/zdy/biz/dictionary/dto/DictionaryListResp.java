package com.zdy.biz.dictionary.dto;

import java.io.Serializable;
import java.util.Date;

public class DictionaryListResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DictionaryResp[] list;
	
	public DictionaryResp[] getList(){
		return list;
	}
	
	public void setList(DictionaryResp[] list){
		this.list = list;
	}
}

