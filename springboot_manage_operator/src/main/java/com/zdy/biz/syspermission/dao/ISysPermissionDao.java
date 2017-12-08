package com.zdy.biz.syspermission.dao;

import java.util.List;

import com.zdy.biz.syspermission.model.SysPermission;
import com.zdy.util.ISuperDao;
public interface ISysPermissionDao  extends ISuperDao<SysPermission> {
	
	List<SysPermission> findChildList(SysPermission permission);
	
	List<SysPermission> findParendList(SysPermission permission);
	
	List<SysPermission> findRolePermissionByRoleIds(SysPermission permission);
	
	List<SysPermission> findUserRolePermissionByRoleIds(SysPermission permission);
	
}
