package com.zdy.role.ctrl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.syspermission.dto.SysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.biz.syspermission.service.ISysPermissionService;
import com.zdy.biz.sysrole.dto.CreateSysRoleReq;
import com.zdy.biz.sysrole.dto.ModifySysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleReq;
import com.zdy.biz.sysrole.dto.SysRoleResp;
import com.zdy.biz.sysrole.service.ISysRoleService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.exception.MyException;
import com.zdy.role.vo.RoleVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;
import com.zdy.util.ZTreeVO;

@Controller
@RequestMapping("role")
public class RoleController extends BaseController {

	@Resource
	private ISysRoleService roleService;

	@Resource
	private ISysPermissionService permissionService;

	@RequestMapping(value = "/torolepage")
	public ModelAndView toSysRolePage() {
		ModelAndView mv = new ModelAndView("role/roleList");

		return mv;
	}

	@RequestMapping(value = "/findrolelist")
	@ResponseBody
	public Result findSysRoleList(RoleVO vo) throws MyException {
		Result res = null;
		try {
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			SysRoleReq d = new SysRoleReq(vo.toSysRole());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			d.setCustId(sessionUser.getCustId());
			ServiceResult<BaseList<SysRoleResp>> serviceResult = roleService.findSysRoleListByPageNo(d);
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getBusinessObject());
			} else {
				return new Result(false, "获取数据错误");
			}
		} catch (Exception e) {
			throw new MyException("获取列表错误", e);
		}
		return res;
	}

	@RequestMapping(value = "/addrole")
	@ResponseBody
	public Result addSysRole(RoleVO vo) throws MyException {
		Result res = null;
		try {
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCustId(sessionUser.getCustId());
			ServiceResult<SysRoleResp> serviceResult = roleService.save(new CreateSysRoleReq(vo.toSysRole()));
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				res = new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("添加信息异常", e);
		}
		return res;
	}

	@RequestMapping(value = "/deleterole")
	@ResponseBody
	public Result deleteSysRole(String id) throws MyException {
		Result res = null;
		try {
			SysRoleReq req = new SysRoleReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<SysRoleResp> serviceResult = roleService.delete(req);
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

	@RequestMapping(value = "/modifyrole")
	@ResponseBody
	public Result modifySysRole(RoleVO vo) throws MyException {
		Result res = null;
		try {
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCustId(sessionUser.getCustId());
			ServiceResult<SysRoleResp> serviceResult = roleService.update(new ModifySysRoleReq(vo.toSysRole()));
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

	@RequestMapping(value = "/getrole")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			SysRoleReq req = new SysRoleReq();
			req.setId(id);
			ServiceResult<SysRoleResp> serviceResult = roleService.getById(req);
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
	@RequestMapping(value = "/findpermissiontree")
	public Result findPermissionTree(Long id) throws MyException {
		Result res = null;
		try {
			SysPermissionReq d = new SysPermissionReq();
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			d.setSysRoleIds(sessionUser.getRoleIds());
			d.setRoleId(id);
			d.setAvailable("0");
			ServiceResult<BaseList<SysPermissionResp>> serviceResult = permissionService.findSysPermissionList(d);
			if (serviceResult.isSuccess()) {
				List<ZTreeVO> list = new ArrayList<ZTreeVO>();
				ZTreeVO vo = null;
				for (SysPermissionResp resp : serviceResult.getBusinessObject().getList()) {
					vo = new ZTreeVO();
					vo.setId(resp.getId());
					vo.setpId(resp.getParentid());
					vo.setName(resp.getName());
					vo.setOpen(false);
					vo.setChecked("true".equals(resp.getChecked()));
					list.add(vo);
				}
				res = new Result(true, list);
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("获取数据异常", e);
		}
		return res;
	}

	@ResponseBody
	@RequestMapping(value = "/saverolepermission")
	public Result saveRolePermission(String roleId, String permissionIds) {

		return null;
	}

}
