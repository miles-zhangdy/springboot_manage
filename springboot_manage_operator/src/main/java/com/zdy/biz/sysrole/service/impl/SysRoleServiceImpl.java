package com.zdy.biz.sysrole.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.sysrole.dao.ISysRoleDao;
import com.zdy.biz.sysrole.dto.CreateSysRoleReq;
import com.zdy.biz.sysrole.dto.ModifySysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleResp;
import com.zdy.biz.sysrole.model.SysRole;
import com.zdy.biz.sysrole.service.ISysRoleService;
import com.zdy.biz.sysrolepermission.dao.ISysRolePermissionDao;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;

@Service
public class SysRoleServiceImpl implements ISysRoleService {

	@Resource
	private ISysRoleDao sysRoleDao;

	@Resource
	private ISysRolePermissionDao rolePermissionDao;

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysRoleResp>> findSysRoleListByPageNo(SysRoleReq req) {
		ServiceResult<BaseList<SysRoleResp>> result = new ServiceResult<BaseList<SysRoleResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<SysRole> baseList = sysRoleDao.findList(req.toSysRole());
		int totalRows = sysRoleDao.count(req.toSysRole());
		int pageSize = req.getPageSize();
		int totalPage = totalRows % pageSize > 0 ? totalRows / pageSize + 1 : totalRows / pageSize + 1;

		List<SysRoleResp> list = new ArrayList<SysRoleResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysRole temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysRoleResp(temp));
			}
		}

		BaseList<SysRoleResp> sysRoleList = new BaseList<SysRoleResp>();
		sysRoleList.setList(list);
		sysRoleList.setCurPage(req.getPage());
		sysRoleList.setPageSize(pageSize);
		sysRoleList.setTotalPage(totalPage);
		sysRoleList.setTotalRows(totalRows);
		return result.success(sysRoleList);
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysRoleResp> save(CreateSysRoleReq req) {
		ServiceResult<SysRoleResp> result = new ServiceResult<SysRoleResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		SysRole d = req.toSysRole();

		int count = sysRoleDao.save(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysRoleResp> delete(SysRoleReq req) {
		ServiceResult<SysRoleResp> result = new ServiceResult<SysRoleResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}
		for (Long id : req.getIds()) {
			rolePermissionDao.deleteByRoleId(id);
		}
		int count = sysRoleDao.delete(req.toSysRole());
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysRoleResp> update(ModifySysRoleReq req) {

		ServiceResult<SysRoleResp> result = new ServiceResult<SysRoleResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = sysRoleDao.update(req.toSysRole());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<SysRoleResp> deleteById(SysRoleReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<SysRoleResp> getById(SysRoleReq req) {
		ServiceResult<SysRoleResp> result = new ServiceResult<SysRoleResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		SysRole SysRole = sysRoleDao.getById(req.getId());
		if (SysRole == null || SysRole.getId() == null) {
			return result.error("获取失败");
		}
		return result.success(new SysRoleResp(SysRole));
	}

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysRoleResp>> findSysRoleList(SysRoleReq req) {
		ServiceResult<BaseList<SysRoleResp>> result = new ServiceResult<BaseList<SysRoleResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}

		List<SysRole> baseList = sysRoleDao.findUserRoleList(req.toSysRole());

		List<SysRoleResp> list = new ArrayList<SysRoleResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysRole temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysRoleResp(temp));
			}
		}

		BaseList<SysRoleResp> sysRoleList = new BaseList<SysRoleResp>();
		sysRoleList.setList(list);
		return result.success(sysRoleList);
	}

}
