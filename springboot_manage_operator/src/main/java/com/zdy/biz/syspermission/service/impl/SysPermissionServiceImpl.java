package com.zdy.biz.syspermission.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;

import com.zdy.biz.syspermission.dao.ISysPermissionDao;
import com.zdy.biz.syspermission.dto.CreateSysPermissionReq;
import com.zdy.biz.syspermission.dto.ModifySysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.biz.syspermission.model.SysPermission;
import com.zdy.biz.syspermission.service.ISysPermissionService;
import com.zdy.biz.sysrolepermission.dao.ISysRolePermissionDao;
import com.zdy.biz.sysrolepermission.model.SysRolePermission;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

import annotation.ReadDataSource;
import annotation.WriteDataSource;

@Service
public class SysPermissionServiceImpl implements ISysPermissionService {

	@Resource
	private ISysPermissionDao sysPermissionDao;

	@Resource
	private ISysRolePermissionDao rolePermission;

	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysPermissionResp>> findSysPermissionListByPageNo(SysPermissionReq req) {
		ServiceResult<BaseList<SysPermissionResp>> result = new ServiceResult<BaseList<SysPermissionResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		List<SysPermission> baseList = sysPermissionDao.findList(req.toSysPermission());
		int totalRows = sysPermissionDao.count(req.toSysPermission());
		int pageSize = req.getPageSize();
		int totalPage = totalRows % pageSize > 0 ? totalRows / pageSize + 1 : totalRows / pageSize + 1;

		List<SysPermissionResp> list = new ArrayList<SysPermissionResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysPermission temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysPermissionResp(temp));
			}
		}

		BaseList<SysPermissionResp> sysPermissionList = new BaseList<SysPermissionResp>();
		sysPermissionList.setList(list);
		sysPermissionList.setCurPage(req.getPage());
		sysPermissionList.setPageSize(pageSize);
		sysPermissionList.setTotalPage(totalPage);
		sysPermissionList.setTotalRows(totalRows);
		return result.success(sysPermissionList);
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysPermissionResp> save(CreateSysPermissionReq req) {
		ServiceResult<SysPermissionResp> result = new ServiceResult<SysPermissionResp>();
		if (req == null) {
			return result.error("保存对象不能为空");
		}
		if ("menu".equals(req.getType())) {
			SysPermission permission = new SysPermission();
			permission.setParentid(req.getParentid());
			permission.setType("button");
			List<SysPermission> list = sysPermissionDao.findList(permission);
			if (!CollectionUtils.isEmpty(list)) {
				return result.error("此处不允许添加菜单");
			}
		}
		if ("button".equals(req.getType())) {
			SysPermission permission = new SysPermission();
			permission.setParentid(req.getParentid());
			permission.setType("menu");
			List<SysPermission> list = sysPermissionDao.findList(permission);
			if (!CollectionUtils.isEmpty(list)) {
				return result.error("此处不允许该操作");
			}
		}
		SysPermission d = req.toSysPermission();

		int count = sysPermissionDao.save(d);
		if (count == 0) {
			return result.error("保存失败");
		}
		return result.success();
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysPermissionResp> delete(SysPermissionReq req) {
		ServiceResult<SysPermissionResp> result = new ServiceResult<SysPermissionResp>();
		if (req == null || ObjectUtils.isEmpty(req.getIds())) {
			return result.error("删除条件不能为空");
		}
		for (Long id : req.getIds()) {
			SysPermission permission = new SysPermission();
			permission.setParentid(id);
			List<SysPermission> list = sysPermissionDao.findList(permission);
			if (!CollectionUtils.isEmpty(list)) {
				return result.error("选中的信息中存在子资源，不允许删除");
			}
		}
		int count = sysPermissionDao.delete(req.toSysPermission());
		SysRolePermission p = new SysRolePermission();
		p.setSysPermissionIds(req.getIds());
		rolePermission.delete(p);
		if (count == 0) {
			return result.error("删除失败");
		}
		return result.success();
	}

	@WriteDataSource
	@Override
	@Transactional
	public ServiceResult<SysPermissionResp> update(ModifySysPermissionReq req) {

		ServiceResult<SysPermissionResp> result = new ServiceResult<SysPermissionResp>();
		if (req == null) {
			return result.error("修改条件不能为空");
		}
		int count = sysPermissionDao.update(req.toSysPermission());
		if (count == 0) {
			return result.error("修改失败");
		}
		return result.success();
	}

	@Override
	public ServiceResult<SysPermissionResp> deleteById(SysPermissionReq req) {

		return null;
	}

	@ReadDataSource
	@Override
	public ServiceResult<SysPermissionResp> getById(SysPermissionReq req) {
		ServiceResult<SysPermissionResp> result = new ServiceResult<SysPermissionResp>();
		if (req == null || req.getId() == null) {
			return result.error("查询条件不能为空");
		}
		SysPermission sysPermission = sysPermissionDao.getById(req.getId());
		if (sysPermission == null || sysPermission.getId() == null) {
			return result.error("获取失败");
		}
		return result.success(new SysPermissionResp(sysPermission));
	}
	
	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysPermissionResp>> findSysPermissionList(SysPermissionReq req) {
		ServiceResult<BaseList<SysPermissionResp>> result = new ServiceResult<BaseList<SysPermissionResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		List<SysPermission> baseList = sysPermissionDao.findRolePermissionByRoleIds(req.toSysPermission());

		List<SysPermissionResp> list = new ArrayList<SysPermissionResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysPermission temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysPermissionResp(temp));
			}
		}

		BaseList<SysPermissionResp> sysPermissionList = new BaseList<SysPermissionResp>();
		sysPermissionList.setList(list);
		return result.success(sysPermissionList);
	}
	
	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysPermissionResp>> findUserSysPermissionList(SysPermissionReq req) {
		ServiceResult<BaseList<SysPermissionResp>> result = new ServiceResult<BaseList<SysPermissionResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		List<SysPermission> baseList = sysPermissionDao.findUserRolePermissionByRoleIds(req.toSysPermission());

		List<SysPermissionResp> list = new ArrayList<SysPermissionResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysPermission temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysPermissionResp(temp));
			}
		}

		BaseList<SysPermissionResp> sysPermissionList = new BaseList<SysPermissionResp>();
		sysPermissionList.setList(list);
		return result.success(sysPermissionList);
	}
	
	
	@ReadDataSource
	@Override
	public ServiceResult<BaseList<SysPermissionResp>> findSysPermissionMenuList(SysPermissionReq req) {
		ServiceResult<BaseList<SysPermissionResp>> result = new ServiceResult<BaseList<SysPermissionResp>>();
		if (req == null) {
			return result.error("查询条件不能为空");
		}
		List<SysPermission> baseList = sysPermissionDao.findList(req.toSysPermission());

		List<SysPermissionResp> list = new ArrayList<SysPermissionResp>();
		if (!CollectionUtils.isEmpty(baseList)) {
			for (SysPermission temp : baseList) {
				if (temp == null) {
					continue;
				}
				list.add(new SysPermissionResp(temp));
			}
		}

		BaseList<SysPermissionResp> sysPermissionList = new BaseList<SysPermissionResp>();
		sysPermissionList.setList(list);
		sysPermissionList.setCurPage(req.getPage());
		return result.success(sysPermissionList);
	}

}
