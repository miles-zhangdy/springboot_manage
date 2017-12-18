package com.zdy.biz.sysuserrole.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.sysuserrole.dao.ISysUserRoleDao;
import com.zdy.biz.sysuserrole.dto.CreateSysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.ModifySysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleResp;
import com.zdy.biz.sysuserrole.model.SysUserRole;
import com.zdy.biz.sysuserrole.service.ISysUserRoleService;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;


@Service
public class SysUserRoleServiceImpl implements ISysUserRoleService {

	@Resource
	private ISysUserRoleDao sysUserRoleDao; 	

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysUserRoleResp>> findSysUserRoleListByPageNo(SysUserRoleReq req) {
		ServiceResult<BaseList<SysUserRoleResp>> result = new ServiceResult<BaseList<SysUserRoleResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<SysUserRole> baseList = sysUserRoleDao.findList(req.toSysUserRole());
		int totalRows = sysUserRoleDao.count(req.toSysUserRole());
		int pageSize = req.getPageSize();
		int totalPage = totalRows % pageSize > 0 ? totalRows / pageSize + 1 : totalRows / pageSize + 1;

		List<SysUserRoleResp> list = new ArrayList<SysUserRoleResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysUserRole temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysUserRoleResp(temp));
			}
		}

		BaseList<SysUserRoleResp> SysUserRoleList = new BaseList<SysUserRoleResp>();
		SysUserRoleList.setList(list);
		SysUserRoleList.setCurPage(req.getPage());
		SysUserRoleList.setPageSize(pageSize);
		SysUserRoleList.setTotalPage(totalPage);
		SysUserRoleList.setTotalRows(totalRows);
		return result.success(SysUserRoleList);
	}
	
	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysUserRoleResp>> findSysUserRoleList(SysUserRoleReq req) {
		ServiceResult<BaseList<SysUserRoleResp>> result = new ServiceResult<BaseList<SysUserRoleResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<SysUserRole> baseList = sysUserRoleDao.findList(req.toSysUserRole());

		List<SysUserRoleResp> list = new ArrayList<SysUserRoleResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysUserRole temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysUserRoleResp(temp));
			}
		}

		BaseList<SysUserRoleResp> SysUserRoleList = new BaseList<SysUserRoleResp>();
		SysUserRoleList.setList(list);
		return result.success(SysUserRoleList);
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysUserRoleResp> save(CreateSysUserRoleReq req) {
		ServiceResult<SysUserRoleResp> result = new ServiceResult<SysUserRoleResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		SysUserRole d = req.toSysUserRole();
		int count = sysUserRoleDao.deleteByUserId(req.getSysUserId());
		if(d.getSysRoleIds() != null && d.getSysRoleIds().length != 0){
			count += sysUserRoleDao.batchSaving(d);
		}
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysUserRoleResp> delete(SysUserRoleReq req) {
		ServiceResult<SysUserRoleResp> result = new ServiceResult<SysUserRoleResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}

		int count = sysUserRoleDao.delete(req.toSysUserRole());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}
	
	@WriteDataSource
	@Transactional
	@Override
	public ServiceResult<SysUserRoleResp> update(ModifySysUserRoleReq req) {

		ServiceResult<SysUserRoleResp> result = new ServiceResult<SysUserRoleResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = sysUserRoleDao.update(req.toSysUserRole());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<SysUserRoleResp> deleteById(SysUserRoleReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<SysUserRoleResp> getById(SysUserRoleReq req) {
		ServiceResult<SysUserRoleResp> result = new ServiceResult<SysUserRoleResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		SysUserRole SysUserRole = sysUserRoleDao.getById(req.getId());
		if(SysUserRole == null || SysUserRole.getId() == null){
			return result.error("获取失败");
		}
		return result.success(new SysUserRoleResp(SysUserRole));
	}

	
}
