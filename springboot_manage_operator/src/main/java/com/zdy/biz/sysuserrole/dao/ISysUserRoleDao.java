package com.zdy.biz.sysuserrole.dao;

import com.zdy.biz.sysuserrole.model.SysUserRole;
import com.zdy.util.ISuperDao;
public interface ISysUserRoleDao  extends ISuperDao<SysUserRole> {
	
	int batchSaving(SysUserRole s);
	
	int deleteByUserId(Long sysUserId);
	
}
