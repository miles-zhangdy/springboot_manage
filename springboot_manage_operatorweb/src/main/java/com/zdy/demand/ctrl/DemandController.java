package com.zdy.demand.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.demand.dto.CreateDemandReq;
import com.zdy.biz.demand.dto.DemandReq;
import com.zdy.biz.demand.dto.DemandResp;
import com.zdy.biz.demand.dto.ModifyDemandReq;
import com.zdy.biz.demand.service.IDemandService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.demand.vo.DemandVO;
import com.zdy.exception.MyException;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;

@Controller
@RequestMapping("/demand/*")
public class DemandController extends BaseController {
	
	@Resource
	private IDemandService demandService;

	@RequestMapping(value = "/todemandpage")
	public ModelAndView toDemandPage() {
		ModelAndView mv = new ModelAndView("demand/demandList");

		return mv;
	}
	
	@RequestMapping(value = "/finddemandlist")
	@ResponseBody
	public Result findDemandList(DemandVO vo) throws MyException {
		Result res = null;
		try {
			DemandReq d = new DemandReq(vo.toDemand());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			ServiceResult<BaseList<DemandResp>> serviceResult = demandService.findDemandListByPageNo(d);
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
	
	@RequestMapping(value = "/adddemand")
	@ResponseBody
	public Result addDemand(DemandVO vo) throws MyException {
		Result res = null;
		try {
			HttpSession session = getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCreateUser(sessionUser.getId());
			ServiceResult<DemandResp> serviceResult = demandService
					.save(new CreateDemandReq(vo.toDemand()));
			if (serviceResult != null && serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				res = new Result(false, "添加失败");
			}
		} catch (Exception e) {
			throw new MyException("添加信息异常", e);
		}
		return res;
	}
	
	@RequestMapping(value = "/deletedemand")
	@ResponseBody
	public Result deleteDemand(String id) throws MyException {
		Result res = null;
		try {
			DemandReq req = new DemandReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<DemandResp> serviceResult = demandService.delete(req);
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

	@RequestMapping(value = "/modifydemand")
	@ResponseBody
	public Result modifyDemand(DemandVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<DemandResp> serviceResult = demandService.update(new ModifyDemandReq(vo.toDemand()));
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
	
	@RequestMapping(value = "/getdemand")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			DemandReq req = new DemandReq();
			req.setId(id);
			ServiceResult<DemandResp> serviceResult = demandService.getById(req);
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
