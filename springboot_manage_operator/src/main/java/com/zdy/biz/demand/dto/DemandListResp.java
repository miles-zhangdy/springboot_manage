package com.zdy.biz.demand.dto;

import com.zdy.biz.demand.model.Demand;
import java.io.Serializable;
import java.util.Date;

public class DemandListResp implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DemandResp[] list;
	
	public DemandResp[] getList(){
		return list;
	}
	
	public void setList(DemandResp[] list){
		this.list = list;
	}
}

