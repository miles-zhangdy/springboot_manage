package com.zdy.biz.demand.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;





import com.zdy.biz.demand.dao.IDemandDao;
import com.zdy.biz.demand.dto.CreateDemandReq;
import com.zdy.biz.demand.dto.DemandReq;
import com.zdy.biz.demand.dto.DemandResp;
import com.zdy.biz.demand.dto.ModifyDemandReq;
import com.zdy.biz.demand.model.Demand;
import com.zdy.biz.demand.service.IDemandService;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;

@Service
public class DemandServiceImpl implements IDemandService {

	@Resource
	private IDemandDao demandDao; 	

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<DemandResp>> findDemandListByPageNo(DemandReq req) {
		ServiceResult<BaseList<DemandResp>> result = new ServiceResult<BaseList<DemandResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<Demand> baseList = demandDao.findList(req.toDemand());
		int totalRows = demandDao.count(req.toDemand());
		int pageSize = req.getPageSize();
		int totalPage = totalRows % pageSize > 0 ? totalRows / pageSize + 1 : totalRows / pageSize + 1;

		List<DemandResp> list = new ArrayList<DemandResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (Demand temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new DemandResp(temp));
			}
		}

		BaseList<DemandResp> demandList = new BaseList<DemandResp>();
		demandList.setList(list);
		demandList.setCurPage(req.getPage());
		demandList.setPageSize(pageSize);
		demandList.setTotalPage(totalPage);
		demandList.setTotalRows(totalRows);
		return result.success(demandList);
	}
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DemandResp> save(CreateDemandReq req) {
		ServiceResult<DemandResp> result = new ServiceResult<DemandResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		Demand d = req.toDemand();

		int count = demandDao.save(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DemandResp> delete(DemandReq req) {
		ServiceResult<DemandResp> result = new ServiceResult<DemandResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}

		int count = demandDao.delete(req.toDemand());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<DemandResp> update(ModifyDemandReq req) {

		ServiceResult<DemandResp> result = new ServiceResult<DemandResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = demandDao.update(req.toDemand());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<DemandResp> deleteById(DemandReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<DemandResp> getById(DemandReq req) {
		ServiceResult<DemandResp> result = new ServiceResult<DemandResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		Demand Demand = demandDao.getById(req.getId());
		if(Demand == null || Demand.getId() == null){
			return result.error("获取失败");
		}
		return result.success(new DemandResp(Demand));
	}

	
}
