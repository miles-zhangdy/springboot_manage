package com.zdy.biz.sysrolepermission.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.sysrolepermission.dao.ISysRolePermissionDao;
import com.zdy.biz.sysrolepermission.dto.CreateSysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.ModifySysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionResp;
import com.zdy.biz.sysrolepermission.model.SysRolePermission;
import com.zdy.biz.sysrolepermission.service.ISysRolePermissionService;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;


@Service
public class SysRolePermissionServiceImpl implements ISysRolePermissionService {

	@Resource
	private ISysRolePermissionDao sysRolePermissionDao; 	

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysRolePermissionResp>> findSysRolePermissionListByPageNo(SysRolePermissionReq req) {
		ServiceResult<BaseList<SysRolePermissionResp>> result = new ServiceResult<BaseList<SysRolePermissionResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<SysRolePermission> baseList = sysRolePermissionDao.findList(req.toSysRolePermission());
		int totalRows = sysRolePermissionDao.count(req.toSysRolePermission());
		int pageSize = req.getPageSize();
		int totalPage = totalRows % pageSize > 0 ? totalRows / pageSize + 1 : totalRows / pageSize + 1;

		List<SysRolePermissionResp> list = new ArrayList<SysRolePermissionResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysRolePermission temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysRolePermissionResp(temp));
			}
		}

		BaseList<SysRolePermissionResp> SysRolePermissionList = new BaseList<SysRolePermissionResp>();
		SysRolePermissionList.setList(list);
		SysRolePermissionList.setCurPage(req.getPage());
		SysRolePermissionList.setPageSize(pageSize);
		SysRolePermissionList.setTotalPage(totalPage);
		SysRolePermissionList.setTotalRows(totalRows);
		return result.success(SysRolePermissionList);
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysRolePermissionResp> save(CreateSysRolePermissionReq req) {
		ServiceResult<SysRolePermissionResp> result = new ServiceResult<SysRolePermissionResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		SysRolePermission d = req.toSysRolePermission();
		sysRolePermissionDao.deleteByRoleId(d.getSysRoleId());
		int count = sysRolePermissionDao.batchSaving(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysRolePermissionResp> delete(SysRolePermissionReq req) {
		ServiceResult<SysRolePermissionResp> result = new ServiceResult<SysRolePermissionResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}

		int count = sysRolePermissionDao.delete(req.toSysRolePermission());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysRolePermissionResp> update(ModifySysRolePermissionReq req) {

		ServiceResult<SysRolePermissionResp> result = new ServiceResult<SysRolePermissionResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = sysRolePermissionDao.update(req.toSysRolePermission());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<SysRolePermissionResp> deleteById(SysRolePermissionReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<SysRolePermissionResp> getById(SysRolePermissionReq req) {
		ServiceResult<SysRolePermissionResp> result = new ServiceResult<SysRolePermissionResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		SysRolePermission SysRolePermission = sysRolePermissionDao.getById(req.getId());
		if(SysRolePermission == null || SysRolePermission.getId() == null){
			return result.error("获取失败");
		}
		return result.success(new SysRolePermissionResp(SysRolePermission));
	}

	
}
