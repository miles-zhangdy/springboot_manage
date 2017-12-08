package com.zdy.biz.demand.service;

import com.zdy.biz.demand.dto.CreateDemandReq;
import com.zdy.biz.demand.dto.DemandReq;
import com.zdy.biz.demand.dto.DemandResp;
import com.zdy.biz.demand.dto.ModifyDemandReq;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface IDemandService {
	
	ServiceResult<BaseList<DemandResp>> findDemandListByPageNo(DemandReq req);
	
	ServiceResult<DemandResp> save(CreateDemandReq req);
	
	ServiceResult<DemandResp> delete(DemandReq req);
	
	ServiceResult<DemandResp> update(ModifyDemandReq req);
	
	ServiceResult<DemandResp> deleteById(DemandReq req);
	
	ServiceResult<DemandResp> getById(DemandReq req);
	
}
