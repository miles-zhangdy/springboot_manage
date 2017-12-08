package com.zdy.biz.sysrolepermission.service;

import com.zdy.biz.sysrolepermission.dto.CreateSysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.ModifySysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface ISysRolePermissionService {
	
	ServiceResult<BaseList<SysRolePermissionResp>> findSysRolePermissionListByPageNo(SysRolePermissionReq req);
	
	ServiceResult<SysRolePermissionResp> save(CreateSysRolePermissionReq req);
	
	ServiceResult<SysRolePermissionResp> delete(SysRolePermissionReq req);
	
	ServiceResult<SysRolePermissionResp> update(ModifySysRolePermissionReq req);
	
	ServiceResult<SysRolePermissionResp> deleteById(SysRolePermissionReq req);
	
	ServiceResult<SysRolePermissionResp> getById(SysRolePermissionReq req);
	
}
