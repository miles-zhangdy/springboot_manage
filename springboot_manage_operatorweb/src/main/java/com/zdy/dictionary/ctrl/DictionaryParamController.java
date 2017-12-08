package com.zdy.dictionary.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.dictionary.dto.CreateDictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamReq;
import com.zdy.biz.dictionary.dto.DictionaryParamResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryParamReq;
import com.zdy.biz.dictionary.service.IDictionaryParamService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.dictionary.vo.DictionaryParamVO;
import com.zdy.exception.MyException;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;

@Controller
@RequestMapping("/dictionaryparam/*")
public class DictionaryParamController extends BaseController {

	@Resource
	private IDictionaryParamService dictionaryparamService;

	@RequestMapping(value = "/todictionaryparampage")
	public ModelAndView toDictionaryParamPage(Long dictionaryId) throws MyException {
		ModelAndView mv = new ModelAndView("dictionary/dictionaryParamList");
		if (dictionaryId == null || dictionaryId == 0L) {
			throw new MyException("请在【字典管理】页面点击查看字典值！");
		}
		mv.addObject("dictionaryId", dictionaryId);
		return mv;
	}

	@RequestMapping(value = "/finddictionaryparamlist")
	@ResponseBody
	public Result findDictionaryParamList(DictionaryParamVO vo) throws MyException {
		Result res = null;
		try {
			DictionaryParamReq d = new DictionaryParamReq(vo.toDictionaryParam());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			ServiceResult<BaseList<DictionaryParamResp>> serviceResult = dictionaryparamService
					.findDictionaryParamListByPageNo(d);
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

	@RequestMapping(value = "/adddictionaryparam")
	@ResponseBody
	public Result addDictionaryParam(DictionaryParamVO vo) throws MyException {
		Result res = null;
		try {
			HttpSession session = getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCreateUser(sessionUser.getId());
			ServiceResult<DictionaryParamResp> serviceResult = dictionaryparamService
					.save(new CreateDictionaryParamReq(vo.toDictionaryParam()));
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

	@RequestMapping(value = "/deletedictionaryparam")
	@ResponseBody
	public Result deleteDictionaryParam(String id) throws MyException {
		Result res = null;
		try {
			DictionaryParamReq req = new DictionaryParamReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<DictionaryParamResp> serviceResult = dictionaryparamService.delete(req);
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

	@RequestMapping(value = "/modifydictionaryparam")
	@ResponseBody
	public Result modifyDictionaryParam(DictionaryParamVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<DictionaryParamResp> serviceResult = dictionaryparamService
					.update(new ModifyDictionaryParamReq(vo.toDictionaryParam()));
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

	@RequestMapping(value = "/getdictionaryparam")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			DictionaryParamReq req = new DictionaryParamReq();
			req.setId(id);
			ServiceResult<DictionaryParamResp> serviceResult = dictionaryparamService.getById(req);
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
