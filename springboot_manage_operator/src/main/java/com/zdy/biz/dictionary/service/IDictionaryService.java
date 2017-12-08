package com.zdy.biz.dictionary.service;

import com.zdy.biz.dictionary.dto.CreateDictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryReq;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface IDictionaryService {
	
	public ServiceResult<BaseList<DictionaryResp>> findDictionaryListByPageNo(DictionaryReq req);
	
	public ServiceResult<DictionaryResp> save(CreateDictionaryReq req);
	
	public ServiceResult<DictionaryResp> delete(DictionaryReq req);
	
	public ServiceResult<DictionaryResp> update(ModifyDictionaryReq req);
	
	public ServiceResult<DictionaryResp> deleteById(DictionaryReq req);
	
	public ServiceResult<DictionaryResp> getById(DictionaryReq req);
	
}
