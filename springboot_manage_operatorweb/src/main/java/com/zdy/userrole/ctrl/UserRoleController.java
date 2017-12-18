package com.zdy.userrole.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.sysrole.dto.SysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleResp;
import com.zdy.biz.sysrole.service.ISysRoleService;
import com.zdy.biz.sysuserrole.dto.CreateSysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.ModifySysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleResp;
import com.zdy.biz.sysuserrole.service.ISysUserRoleService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.exception.MyException;
import com.zdy.userrole.vo.UserRoleVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;


@Controller
@RequestMapping("userrole")
public class UserRoleController extends BaseController {

	@Resource
	private ISysUserRoleService userroleService;

	@Resource
	private ISysRoleService roleService;

	@RequestMapping(value = "/touserrolepage")
	public ModelAndView toSysUserRolePage() {
		ModelAndView mv = new ModelAndView("userrole/userrolelist");

		return mv;
	}

	/*
	 * @RequestMapping(value = "/finduserrolelist")
	 * 
	 * @ResponseBody public Result findSysUserRoleList(UserRoleVO vo) throws
	 * MyException { Result res = null; try { SysUserRoleReq d = new
	 * SysUserRoleReq(vo.toSysUserRole()); d.setBeginIndex((vo.getPage() - 1) *
	 * vo.getPageSize()); d.setPage(vo.getPage());
	 * d.setPageSize(vo.getPageSize()); ServiceResult<BaseList<SysUserRoleResp>>
	 * serviceResult = userroleService.findSysUserRoleListByPageNo(d); if
	 * (serviceResult.isSuccess()) { res = new Result(true,
	 * serviceResult.getBusinessObject()); } else { return new Result(false,
	 * "获取数据错误"); } } catch (Exception e) { throw new MyException("获取列表错误", e);
	 * } return res; }
	 */
	@RequestMapping(value = "/adduserrole")
	@ResponseBody
	public Result addSysUserRole(Long sysUserId, @RequestParam(required = false, value = "roleIds[]") Long[] roleIds)
			throws MyException {
		Result res = null;
		try {
			CreateSysUserRoleReq req = new CreateSysUserRoleReq();
			req.setSysUserId(sysUserId);
			req.setSysRoleIds(roleIds);
			ServiceResult<SysUserRoleResp> serviceResult = userroleService.save(req);
			if(serviceResult.isSuccess()){
				res = new Result(true, serviceResult.getMsg());
			}else{
				res = new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("添加信息异常", e);
		}
		return res;
	}

	@RequestMapping(value = "/deleteuserrole")
	@ResponseBody
	public Result deleteSysUserRole(String id) throws MyException {
		Result res = null;
		try {
			SysUserRoleReq req = new SysUserRoleReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<SysUserRoleResp> serviceResult = userroleService.delete(req);
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("删除数据异常", e);
		}
		return res;
	}

	@RequestMapping(value = "/modifyuserrole")
	@ResponseBody
	public Result modifySysUserRole(UserRoleVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<SysUserRoleResp> serviceResult = userroleService
					.update(new ModifySysUserRoleReq(vo.toSysUserRole()));
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("修改数据异常", e);
		}
		return res;
	}

	@RequestMapping(value = "/getuserrole")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			SysUserRoleReq req = new SysUserRoleReq();
			req.setId(id);
			ServiceResult<SysUserRoleResp> serviceResult = userroleService.getById(req);
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getBusinessObject());
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("获取数据异常", e);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/finduserrolelist")
	public Result findUserRoleList(Long sysUserId) throws MyException {
		Result res = new Result();
		try {
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			SysRoleReq req = new SysRoleReq();
			req.setUserId(sysUserId);
			req.setCustId(sessionUser.getCustId());
			ServiceResult<BaseList<SysRoleResp>> serviceResult = roleService.findSysRoleList(req);
			if (serviceResult.isSuccess()) {
				res.setSuccess(true);
				res.setData(serviceResult.getBusinessObject());
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("获取角色出错", e);
		}
		return res;
	}
}
