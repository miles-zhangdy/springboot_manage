package com.zdy.dictionary.ctrl;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.dictionary.dto.CreateDictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryReq;
import com.zdy.biz.dictionary.dto.DictionaryResp;
import com.zdy.biz.dictionary.dto.ModifyDictionaryReq;
import com.zdy.biz.dictionary.service.IDictionaryService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.dictionary.vo.DictionaryVO;
import com.zdy.exception.MyException;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;



@Controller
@RequestMapping("dictionary")
public class DictionaryController extends BaseController {
	
	@Resource
	private IDictionaryService dictionaryService;

	@RequestMapping(value = "/todictionarypage")
	public ModelAndView toDictionaryPage() {
		ModelAndView mv = new ModelAndView("dictionary/dictionaryList");

		return mv;
	}
	
	@RequestMapping(value = "/finddictionarylist")
	@ResponseBody
	public Result findDictionaryList(DictionaryVO vo) throws MyException {
		Result res = null;
		try {
			DictionaryReq d = new DictionaryReq(vo.toDictionary());
			d.setBeginIndex((vo.getPage() - 1) * vo.getPageSize());
			d.setPage(vo.getPage());
			d.setPageSize(vo.getPageSize());
			ServiceResult<BaseList<DictionaryResp>> serviceResult = dictionaryService.findDictionaryListByPageNo(d);
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
	
	@RequestMapping(value = "/adddictionary")
	@ResponseBody
	public Result addDictionary(DictionaryVO vo) throws MyException {
		Result res = null;
		try {
			HttpSession session = getSession();
			SessionUser sessionUser = (SessionUser) session.getAttribute(Constant.ENVIRONMENT_USER);
			vo.setCreateUser(sessionUser.getId());
			ServiceResult<DictionaryResp> serviceResult = dictionaryService
					.save(new CreateDictionaryReq(vo.toDictionary()));
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
	
	@RequestMapping(value = "/deletedictionary")
	@ResponseBody
	public Result deleteDictionary(String id) throws MyException {
		Result res = null;
		try {
			DictionaryReq req = new DictionaryReq();
			String[] array = id.split(",");
			Long[] a = new Long[array.length];
			int i = 0;
			for (String str : array) {
				a[i++] = Long.parseLong(str);
			}
			req.setIds(a);
			ServiceResult<DictionaryResp> serviceResult = dictionaryService.delete(req);
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

	@RequestMapping(value = "/modifydictionary")
	@ResponseBody
	public Result modifyDictionary(DictionaryVO vo) throws MyException {
		Result res = null;
		try {
			ServiceResult<DictionaryResp> serviceResult = dictionaryService.update(new ModifyDictionaryReq(vo.toDictionary()));
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
	
	@RequestMapping(value = "/getdictionary")
	@ResponseBody
	public Result getUser(Long id) throws MyException {
		Result res = null;
		try {
			DictionaryReq req = new DictionaryReq();
			req.setId(id);
			ServiceResult<DictionaryResp> serviceResult = dictionaryService.getById(req);
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
