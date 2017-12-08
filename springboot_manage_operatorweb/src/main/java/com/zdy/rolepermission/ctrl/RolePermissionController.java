package com.zdy.rolepermission.ctrl;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.sysrolepermission.dto.CreateSysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.ModifySysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionReq;
import com.zdy.biz.sysrolepermission.dto.SysRolePermissionResp;
import com.zdy.biz.sysrolepermission.service.ISysRolePermissionService;
import com.zdy.exception.MyException;
import com.zdy.rolepermission.vo.RolePermissionVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;


@Controller
@RequestMapping("rolepermission")
public class RolePermissionController extends BaseController {

	@Resource
	private ISysRolePermissionService rolepermissionService;

	@RequestMapping(value = "/torolepermissionpage")
	public ModelAndView toSysRolePermissionPage() {
		ModelAndView mv = new ModelAndView("rolepermission/rolepermission");

		return mv;
	}

	@RequestMapping(value = "/findrolepermissionlist")
	@ResponseBody
	public Result findSysRolePermissionList(RolePermissionVO vo) throws MyException {
		Result res = null;
		try {
			SysRolePermissionReq d = new SysRolePermissionReq(vo.toSysRolePermission());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			ServiceResult<BaseList<SysRolePermissionResp>> serviceResult = rolepermissionService
					.findSysRolePermissionListByPageNo(d);
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getBusinessObject());
			} else {
				return new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("获取列表错误", e);
		}
		return res;
	}

	@RequestMapping(value = "/addrolepermission")
	@ResponseBody
	public Result addSysRolePermission(RolePermissionVO vo, @RequestParam(required = false, value = "sysPermissionIds[]") Long[] sysPermissionIds) throws MyException {
		Result res = null;
		try {
			CreateSysRolePermissionReq req = new CreateSysRolePermissionReq(vo.toSysRolePermission());
			req.setSysPermissionIds(sysPermissionIds);
			ServiceResult<SysRolePermissionResp> serviceResult = rolepermissionService
					.save(req);
			if (serviceResult != null && serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				res = new Result(false, serviceResult.getMsg());
			}
		} catch (Exception e) {
			throw new MyException("添加信息异常", e);
		}
		return res;
	}

	@RequestMapping(value = "/deleterolepermission")
	@ResponseBody
	public Result deleteSysRolePermission(String id) throws MyException {
		Result res = null;
		try {
			SysRolePermissionReq req = new SysRolePermissionReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<SysRolePermissionResp> serviceResult = rolepermissionService.delete(req);
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

	@RequestMapping(value = "/modifyrolepermission")
	@ResponseBody
	public Result modifySysRolePermission(RolePermissionVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<SysRolePermissionResp> serviceResult = rolepermissionService
					.update(new ModifySysRolePermissionReq(vo.toSysRolePermission()));
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

	@RequestMapping(value = "/getrolepermission")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			SysRolePermissionReq req = new SysRolePermissionReq();
			req.setId(id);
			ServiceResult<SysRolePermissionResp> serviceResult = rolepermissionService.getById(req);
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

}
