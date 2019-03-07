package com.zdy.biz.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.dictionary.dao.IDictionaryParamDao;
import com.zdy.biz.dictionary.dto.CreateDictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryParamReq;
import com.zdy.biz.dictionary.model.DictionaryParam;
import com.zdy.biz.dictionary.service.IDictionaryParamService;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;

@Service
public class DictionaryParamServiceImpl implements IDictionaryParamService {

	@Resource
	private IDictionaryParamDao dictionaryParamDao; 	

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<DictionaryParamResp>> findDictionaryParamListByPageNo(DictionaryParamReq req) {
		ServiceResult<BaseList<DictionaryParamResp>> result = new ServiceResult<BaseList<DictionaryParamResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		PageHelper.startPage(req.getPage(), req.getPageSize());
		List<DictionaryParam> baseList = dictionaryParamDao.findList(req.toDictionaryParam());
		PageInfo<DictionaryParam> pageInfo = new PageInfo<DictionaryParam>(baseList);
		List<DictionaryParamResp> list = new ArrayList<DictionaryParamResp>();
		if (!CollectionUtils.isEmpty(pageInfo.getList())) {
			for (DictionaryParam temp : pageInfo.getList()) {
				if (temp == null) {
					continue;
				}
				list.add(new DictionaryParamResp(temp));
			}
		}

		BaseList<DictionaryParamResp> dictionaryParamList = new BaseList<DictionaryParamResp>();
		dictionaryParamList.setList(list);
		dictionaryParamList.setCurPage(req.getPage());
		dictionaryParamList.setPageSize(req.getPageSize());
		dictionaryParamList.setTotalPage(pageInfo.getPages());
		dictionaryParamList.setTotalRows(pageInfo.getTotal());
		return result.success(dictionaryParamList);
	}
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryParamResp> save(CreateDictionaryParamReq req) {
		ServiceResult<DictionaryParamResp> result = new ServiceResult<DictionaryParamResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		DictionaryParam d = req.toDictionaryParam();

		int count = dictionaryParamDao.save(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryParamResp> delete(DictionaryParamReq req) {
		ServiceResult<DictionaryParamResp> result = new ServiceResult<DictionaryParamResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}

		int count = dictionaryParamDao.delete(req.toDictionaryParam());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryParamResp> update(ModifyDictionaryParamReq req) {

		ServiceResult<DictionaryParamResp> result = new ServiceResult<DictionaryParamResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = dictionaryParamDao.update(req.toDictionaryParam());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<DictionaryParamResp> deleteById(DictionaryParamReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<DictionaryParamResp> getById(DictionaryParamReq req) {
		ServiceResult<DictionaryParamResp> result = new ServiceResult<DictionaryParamResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		DictionaryParam DictionaryParam = dictionaryParamDao.getById(req.getId());
		if(DictionaryParam == null || DictionaryParam.getId() == null){
			return result.error("获取失败");
		}
		return result.success(new DictionaryParamResp(DictionaryParam));
	}

	
}
