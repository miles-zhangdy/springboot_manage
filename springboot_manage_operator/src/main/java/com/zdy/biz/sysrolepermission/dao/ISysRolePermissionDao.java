package com.zdy.biz.sysrolepermission.dao;

import com.zdy.biz.sysrolepermission.model.SysRolePermission;
import com.zdy.util.ISuperDao;
public interface ISysRolePermissionDao  extends ISuperDao<SysRolePermission> {
	
	int batchSaving(SysRolePermission s);
	
	int deleteByRoleId(Long sysRoleId);
	
}
