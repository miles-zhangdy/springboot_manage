package com.zdy.permission.ctrl;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.syspermission.dto.CreateSysPermissionReq;
import com.zdy.biz.syspermission.dto.ModifySysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.biz.syspermission.service.ISysPermissionService;
import com.zdy.exception.MyException;
import com.zdy.permission.vo.PermissionVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;

@Controller
@RequestMapping("permission")
public class PermissionController extends BaseController {

	@Resource
	private ISysPermissionService permissionService;

	@RequestMapping(value = "/topermissionpage")
	public ModelAndView toSysPermissionPage(String parentId) {
		ModelAndView mv = new ModelAndView("permission/permissionList");
		mv.addObject("parentId", parentId);
		if(StringUtils.isBlank(parentId)){
			mv.addObject("parentId", 0);
		}
		return mv;
	}

	@RequestMapping(value = "/findpermissionlist")
	@ResponseBody
	public Result findSysPermissionList(PermissionVO vo, String parentId) throws MyException {
		Result res = null;
		try {
			SysPermissionReq d = new SysPermissionReq(vo.toSysPermission());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			if(StringUtils.isNotBlank(parentId)){
				d.setParentid(Long.parseLong(parentId));
			}else{
				d.setParentid(0L);
			}
			ServiceResult<BaseList<SysPermissionResp>> serviceResult = permissionService
					.findSysPermissionListByPageNo(d);
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

	@RequestMapping(value = "/addpermission")
	@ResponseBody
	public Result addSysPermission(PermissionVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<SysPermissionResp> serviceResult = permissionService
					.save(new CreateSysPermissionReq(vo.toSysPermission()));
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

	@RequestMapping(value = "/deletepermission")
	@ResponseBody
	public Result deleteSysPermission(String id) throws MyException {
		Result res = null;
		try {
			SysPermissionReq req = new SysPermissionReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<SysPermissionResp> serviceResult = permissionService.delete(req);
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

	@RequestMapping(value = "/modifypermission")
	@ResponseBody
	public Result modifySysPermission(PermissionVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<SysPermissionResp> serviceResult = permissionService
					.update(new ModifySysPermissionReq(vo.toSysPermission()));
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

	@RequestMapping(value = "/getpermission")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			SysPermissionReq req = new SysPermissionReq();
			req.setId(id);
			ServiceResult<SysPermissionResp> serviceResult = permissionService.getById(req);
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
