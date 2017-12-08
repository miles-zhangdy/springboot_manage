package com.zdy.biz.dictionary.service;

import com.zdy.biz.dictionary.dto.CreateDictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryParamReq;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface IDictionaryParamService {
	
	ServiceResult<BaseList<DictionaryParamResp>> findDictionaryParamListByPageNo(DictionaryParamReq req);
	
	ServiceResult<DictionaryParamResp> save(CreateDictionaryParamReq req);
	
	ServiceResult<DictionaryParamResp> delete(DictionaryParamReq req);
	
	ServiceResult<DictionaryParamResp> update(ModifyDictionaryParamReq req);
	
	ServiceResult<DictionaryParamResp> deleteById(DictionaryParamReq req);
	
	ServiceResult<DictionaryParamResp> getById(DictionaryParamReq req);
	
}
