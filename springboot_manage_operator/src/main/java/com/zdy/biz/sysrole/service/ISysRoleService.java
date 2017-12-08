package com.zdy.biz.sysrole.service;

import com.zdy.biz.sysrole.dto.CreateSysRoleReq;
import com.zdy.biz.sysrole.dto.ModifySysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface ISysRoleService {
	
	ServiceResult<BaseList<SysRoleResp>> findSysRoleListByPageNo(SysRoleReq req);
	
	ServiceResult<SysRoleResp> save(CreateSysRoleReq req);
	
	ServiceResult<SysRoleResp> delete(SysRoleReq req);
	
	ServiceResult<SysRoleResp> update(ModifySysRoleReq req);
	
	ServiceResult<SysRoleResp> deleteById(SysRoleReq req);
	
	ServiceResult<SysRoleResp> getById(SysRoleReq req);
	
	ServiceResult<BaseList<SysRoleResp>> findSysRoleList(SysRoleReq req);
	
}
