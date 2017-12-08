package com.zdy.biz.sysrole.dao;

import java.util.List;

import com.zdy.biz.sysrole.model.SysRole;
import com.zdy.util.ISuperDao;

public interface ISysRoleDao extends ISuperDao<SysRole> {
	
	List<SysRole> findUserRoleList(SysRole t);
	
}
