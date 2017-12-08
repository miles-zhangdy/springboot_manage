package com.zdy.biz.sysuserrole.service;

import com.zdy.biz.sysuserrole.dto.CreateSysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.ModifySysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface ISysUserRoleService {
	
	ServiceResult<BaseList<SysUserRoleResp>> findSysUserRoleListByPageNo(SysUserRoleReq req);
	
	ServiceResult<SysUserRoleResp> save(CreateSysUserRoleReq req);
	
	ServiceResult<SysUserRoleResp> delete(SysUserRoleReq req);
	
	ServiceResult<SysUserRoleResp> update(ModifySysUserRoleReq req);
	
	ServiceResult<SysUserRoleResp> deleteById(SysUserRoleReq req);
	
	ServiceResult<SysUserRoleResp> getById(SysUserRoleReq req);
	
	ServiceResult<BaseList<SysUserRoleResp>> findSysUserRoleList(SysUserRoleReq req);
	
}
