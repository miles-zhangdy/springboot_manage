package com.zdy.biz.dictionary.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.zdy.biz.dictionary.model.DictionaryParam;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.dictionary.dao.IDictionaryDao;
import com.zdy.biz.dictionary.dto.CreateDictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryReq;
import com.zdy.biz.dictionary.model.Dictionary;
import com.zdy.biz.dictionary.service.IDictionaryService;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;

@Service
public class DictionaryServiceImpl implements IDictionaryService {

	@Resource
	private IDictionaryDao dictionaryDao; 	

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<DictionaryResp>> findDictionaryListByPageNo(DictionaryReq req) {
		ServiceResult<BaseList<DictionaryResp>> result = new ServiceResult<BaseList<DictionaryResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		PageHelper.startPage(req.getPage(), req.getPageSize());
		List<Dictionary> baseList = dictionaryDao.findList(req.toDictionary());
		PageInfo<Dictionary> pageInfo = new PageInfo<>(baseList);
		List<DictionaryResp> list = new ArrayList<DictionaryResp>();
		if (!CollectionUtils.isEmpty(pageInfo.getList())) {
			for (Dictionary temp : pageInfo.getList()) {
				if (temp == null) {
					continue;
				}
				list.add(new DictionaryResp(temp));
			}
		}

		BaseList<DictionaryResp> dictionaryList = new BaseList<DictionaryResp>();
		dictionaryList.setList(list);
		dictionaryList.setCurPage(req.getPage());
		dictionaryList.setPageSize(req.getPageSize());
		dictionaryList.setTotalPage(pageInfo.getPages());
		dictionaryList.setTotalRows(pageInfo.getTotal());
		return result.success(dictionaryList);
	}
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryResp> save(CreateDictionaryReq req) {
		ServiceResult<DictionaryResp> result = new ServiceResult<DictionaryResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		Dictionary d = req.toDictionary();

		int count = dictionaryDao.save(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryResp> delete(DictionaryReq req) {
		ServiceResult<DictionaryResp> result = new ServiceResult<DictionaryResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}

		int count = dictionaryDao.delete(req.toDictionary());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DictionaryResp> update(ModifyDictionaryReq req) {

		ServiceResult<DictionaryResp> result = new ServiceResult<DictionaryResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = dictionaryDao.update(req.toDictionary());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<DictionaryResp> deleteById(DictionaryReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<DictionaryResp> getById(DictionaryReq req) {
		ServiceResult<DictionaryResp> result = new ServiceResult<DictionaryResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		Dictionary Dictionary = dictionaryDao.getById(req.getId());
		if(Dictionary == null || Dictionary.getId() == null){
			return result.error("获取失败");
		}
		return result.success(new DictionaryResp(Dictionary));
	}

	
}
