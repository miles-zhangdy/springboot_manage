package com.zdy.biz.siteparameter.service;

import com.zdy.biz.siteparameter.dto.CreateSiteParameterReq;
import com.zdy.biz.siteparameter.dto.ModifySiteParameterReq;
import com.zdy.biz.siteparameter.dto.SiteParameterReq;
import com.zdy.biz.siteparameter.dto.SiteParameterResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface ISiteParameterService {
	
	ServiceResult<BaseList<SiteParameterResp>> findSiteParameterListByPageNo(SiteParameterReq req);
	
	ServiceResult<SiteParameterResp> save(CreateSiteParameterReq req);
	
	ServiceResult<SiteParameterResp> delete(SiteParameterReq req);
	
	ServiceResult<SiteParameterResp> update(ModifySiteParameterReq req);
	
	ServiceResult<SiteParameterResp> deleteById(SiteParameterReq req);
	
	ServiceResult<SiteParameterResp> getById(SiteParameterReq req);
	
}
