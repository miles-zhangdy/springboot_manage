package com.zdy.biz.syspermission.service;

import com.zdy.biz.syspermission.dto.CreateSysPermissionReq;
import com.zdy.biz.syspermission.dto.ModifySysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface ISysPermissionService {
	
	ServiceResult<BaseList<SysPermissionResp>> findSysPermissionListByPageNo(SysPermissionReq req);
	
	ServiceResult<SysPermissionResp> save(CreateSysPermissionReq req);
	
	ServiceResult<SysPermissionResp> delete(SysPermissionReq req);
	
	ServiceResult<SysPermissionResp> update(ModifySysPermissionReq req);
	
	ServiceResult<SysPermissionResp> deleteById(SysPermissionReq req);
	
	ServiceResult<SysPermissionResp> getById(SysPermissionReq req);
	
	ServiceResult<BaseList<SysPermissionResp>> findSysPermissionList(SysPermissionReq req);
	
	ServiceResult<BaseList<SysPermissionResp>> findUserSysPermissionList(SysPermissionReq req);
	
	ServiceResult<BaseList<SysPermissionResp>> findSysPermissionMenuList(SysPermissionReq req);
	
}
