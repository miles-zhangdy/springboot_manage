package com.zdy.biz.user.service;

import com.zdy.biz.user.dto.CreateUserReq;
import com.zdy.biz.user.dto.ModifyUserReq;
import com.zdy.biz.user.dto.UserReq;
import com.zdy.biz.user.dto.UserResp;
import com.zdy.util.BaseList;
import com.zdy.util.ServiceResult;

public interface IUserService {
	
	ServiceResult<BaseList<UserResp>> findUserListByPageNo(UserReq req);
	
	ServiceResult<UserResp> save(CreateUserReq req);
	
	ServiceResult<UserResp> delete(UserReq req);
	
	ServiceResult<UserResp> update(ModifyUserReq req);
	
	ServiceResult<UserResp> deleteById(UserReq req);
	
	ServiceResult<UserResp> getById(UserReq req);
	
	ServiceResult<UserResp> login(UserReq req);
	
	ServiceResult<UserResp> fetch(UserReq req);
}
