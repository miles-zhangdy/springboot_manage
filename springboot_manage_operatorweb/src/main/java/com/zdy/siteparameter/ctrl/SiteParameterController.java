package com.zdy.siteparameter.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.siteparameter.dto.CreateSiteParameterReq;
import com.zdy.biz.siteparameter.dto.ModifySiteParameterReq;
import com.zdy.biz.siteparameter.dto.SiteParameterReq;
import com.zdy.biz.siteparameter.dto.SiteParameterResp;
import com.zdy.biz.siteparameter.service.ISiteParameterService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.exception.MyException;
import com.zdy.siteparameter.vo.SiteParameterVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;


@Controller
@RequestMapping("siteparameter")
public class SiteParameterController extends BaseController {

	@Resource
	private ISiteParameterService siteParameterService;

	@RequestMapping(value = "/tositeparameterpage")
	public ModelAndView toSiteParameterPage() {
		ModelAndView mv = new ModelAndView("siteparameter/parameterList");

		return mv;
	}

	@RequestMapping(value = "/findsiteparameterlist")
	@ResponseBody
	public Result findSiteParameterList(SiteParameterVO vo) throws MyException {
		Result res = null;
		try {
			SiteParameterReq d = new SiteParameterReq(vo.toSiteParameter());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			ServiceResult<BaseList<SiteParameterResp>> serviceResult = siteParameterService
					.findSiteParameterListByPageNo(d);
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

	@RequestMapping(value = "/addsiteparameter")
	@ResponseBody
	public Result addSiteParameter(SiteParameterVO vo) throws MyException {
		Result res = null;
		try {
			HttpSession session = getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCreateUser(sessionUser.getId());
			ServiceResult<SiteParameterResp> serviceResult = siteParameterService
					.save(new CreateSiteParameterReq(vo.toSiteParameter()));
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

	@RequestMapping(value = "/deletesiteparameter")
	@ResponseBody
	public Result deleteSiteParameter(String id) throws MyException {
		Result res = null;
		try {
			SiteParameterReq req = new SiteParameterReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<SiteParameterResp> serviceResult = siteParameterService.delete(req);
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

	@RequestMapping(value = "/modifysiteparameter")
	@ResponseBody
	public Result modifySiteParameter(SiteParameterVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<SiteParameterResp> serviceResult = siteParameterService
					.update(new ModifySiteParameterReq(vo.toSiteParameter()));
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

	@RequestMapping(value = "/getsiteparameter")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			SiteParameterReq req = new SiteParameterReq();
			req.setId(id);
			ServiceResult<SiteParameterResp> serviceResult = siteParameterService.getById(req);
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
