package com.zdy.user.ctrl;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.syspermission.dto.SysPermissionReq;
import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.biz.syspermission.service.ISysPermissionService;
import com.zdy.biz.sysuserrole.dto.SysUserRoleReq;
import com.zdy.biz.sysuserrole.dto.SysUserRoleResp;
import com.zdy.biz.sysuserrole.service.ISysUserRoleService;
import com.zdy.biz.user.dto.CreateUserReq;
import com.zdy.biz.user.dto.ModifyUserReq;
import com.zdy.biz.user.dto.UserReq;
import com.zdy.biz.user.dto.UserResp;
import com.zdy.biz.user.service.IUserService;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.exception.MyException;
import com.zdy.user.vo.UserVO;
import com.zdy.util.BaseController;
import com.zdy.util.BaseList;
import com.zdy.util.Result;
import com.zdy.util.ServiceResult;

import net.sf.json.JSONArray;

@Controller
@RequestMapping("user/*")
public class UserController extends BaseController {

	@Resource
	private IUserService userService;

	@Resource
	private ISysPermissionService permissionService;

	@Resource
	private ISysUserRoleService userRoleService;

	@RequestMapping(value = "/toindex")
	public ModelAndView toIndex() {
		ModelAndView mv = new ModelAndView("index");
		return mv;
	}

	@RequestMapping(value = "/touserpage")
	public ModelAndView toUserPage() {
		ModelAndView mv = new ModelAndView("user/userList");

		return mv;
	}
	
	@RequestMapping(value = "/touservalidate")
	public ModelAndView toUserValidate() {
		ModelAndView mv = new ModelAndView("user/userValidate");

		return mv;
	}


	@RequestMapping(value = "/toregisterpage")
	public String toRegisterPage() {
		return "login/register";
	}

	@RequestMapping(value = "/finduserlist")
	@ResponseBody
	public Result findUserList(UserVO userVO) throws MyException {
		Result res = null;
		try {

			UserReq d = new UserReq(userVO.toUser());
			d.setBeginIndex((userVO.getPage() - 1) * userVO.getPageSize());
			d.setPage(userVO.getPage());
			d.setPageSize(userVO.getPageSize());
			SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
			d.setParentId(sessionUser.getId());
			if(userVO.getParentId() != null){
				d.setParentId(userVO.getParentId());
			}
			ServiceResult<BaseList<UserResp>> serviceResult = userService.findUserListByPageNo(d);
			if (serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getBusinessObject());
			} else {
				return new Result(false, "获取数据错误");
			}
		} catch (Exception e) {
			throw new MyException("获取列表失败", e);
		}
		return res;
	}

	@RequestMapping(value = "/adduser")
	@ResponseBody
	public Result addUser(UserVO vo) {
		Result res = null;
		HttpSession session = getSession();
		SessionUser sessionUser = (SessionUser) session.getAttribute(Constant.ENVIRONMENT_USER);
		vo.setCreateUser(sessionUser.getUserName());
		vo.setParentId(sessionUser.getId());
		vo.setUserValidate(1);
		ServiceResult<UserResp> serviceResult = userService.save(new CreateUserReq(vo.toUser()));

		if (serviceResult != null && serviceResult.isSuccess()) {
			res = new Result(true, serviceResult.getMsg());
		} else {
			res = new Result(false, "添加失败");
		}
		return res;
	}
	
	@RequestMapping(value = "/registeruser")
	@ResponseBody
	public Result registerUser(UserVO vo) throws MyException {
		Result res = null;
		try {
			vo.setCreateUser(vo.getUserName());
			vo.setParentId(1L);
			vo.setPassword(vo.getUserName());
			ServiceResult<UserResp> serviceResult = userService.save(new CreateUserReq(vo.toUser()));
			
			if (serviceResult != null && serviceResult.isSuccess()) {
				res = new Result(true, serviceResult.getMsg());
			} else {
				res = new Result(false, "注册失败");
			}
		} catch (Exception e) {
			throw new MyException("注册失败", e);
		}
		return res;
	}

	@RequestMapping(value = "/deleteuser")
	@ResponseBody
	public Result deleteUser(String id) {
		Result res = null;
		UserReq req = new UserReq();
		String[] array = id.split(",");
		Long[] a = new Long[array.length];
		int i = 0;
		for (String str : array) {
			a[i++] = Long.parseLong(str);
		}
		req.setIds(a);
		ServiceResult<UserResp> serviceResult = userService.delete(req);
		if (serviceResult.isSuccess()) {
			res = new Result(true, serviceResult.getMsg());
		} else {
			return new Result(false, serviceResult.getMsg());
		}
		return res;
	}

	@RequestMapping(value = "/modifyuser")
	@ResponseBody
	public Result modifyUser(UserVO userVO) {
		Result res = null;

		ServiceResult<UserResp> serviceResult = userService.update(new ModifyUserReq(userVO.toUser()));
		if (serviceResult.isSuccess()) {
			res = new Result(true, serviceResult.getMsg());
		} else {
			return new Result(false, serviceResult.getMsg());
		}
		return res;
	}

	@RequestMapping(value = "/getuser")
	@ResponseBody
	public Result getUser(Long id) {
		Result res = null;
		UserReq req = new UserReq();
		req.setId(id);
		ServiceResult<UserResp> serviceResult = userService.getById(req);
		if (serviceResult.isSuccess()) {
			res = new Result(true, serviceResult.getBusinessObject());
		} else {
			return new Result(false, serviceResult.getMsg());
		}
		return res;
	}

	@RequestMapping(value = "/getuserbyname")
	@ResponseBody
	public Result getUserByName(String userName) {
		Result res = null;
		UserReq req = new UserReq();
		req.setUserName(userName);
		ServiceResult<UserResp> serviceResult = userService.fetch(req);
		if (serviceResult.isSuccess()) {
			if (serviceResult.getBusinessObject() != null && serviceResult.getBusinessObject().getId() != null) {
				res = new Result(true, false);
			} else {
				res = new Result(true, true);
			}
		} else {
			return new Result(false, serviceResult.getMsg());
		}
		return res;
	}

	@RequestMapping(value = "/tologin")
	public ModelAndView toLogin() throws MyException {
		ModelAndView mv = new ModelAndView("login/login");
		SessionUser sessionUser = (SessionUser) getSession().getAttribute(Constant.ENVIRONMENT_USER);
		if (sessionUser != null && StringUtils.isNotBlank(sessionUser.getId() + "")) {
			mv = new ModelAndView("index");
		}
		return mv;
	}

	@RequestMapping(value = "/logout")
	public String logout() throws MyException {
		getSession().setAttribute(Constant.ENVIRONMENT_USER, null);
		return "login/login";
	}

	@RequestMapping(value = "/getremember")
	@ResponseBody
	public Result getRemember() throws MyException {
		Map<String, Object> map = new HashMap<String, Object>();
		Cookie[] cookies = getRequest().getCookies();
		String username = null;
		String password = null;
		String remember = null;
		for (int i = 0; i < cookies.length; i++) {
			if ("zdy_operator_login_username".equals(cookies[i].getName())) {
				username = cookies[i].getValue();
			} else if ("zdy_operator_login_password".equals(cookies[i].getName())) {
				password = cookies[i].getValue();
			} else if ("zdy_operator_login_remember".equals(cookies[i].getName())) {
				remember = cookies[i].getValue();
			}
		}
		if ("true".equals(remember)) {
			map.put("username", username);
			map.put("password", password);
		} else {
			map.put("username", "");
			map.put("password", "");
		}
		map.put("remember", remember);
		return new Result(true, map);
	}

	@RequestMapping(value = "/login")
	@ResponseBody
	public Result login(UserVO userVO, String remember, String validateCode, HttpServletResponse response) throws MyException {
		Result res = null;
		if (userVO == null) {
			throw new MyException("登录对象不能为空");
		}
		try {
			String sessionValidateCode = (String) getSession().getAttribute(Constant.VALIDATE_CODE);
			if(sessionValidateCode == null || !sessionValidateCode.equals(validateCode)){
				throw new MyException("验证码错误，请重新登录！！");
			}
			UserReq req = new UserReq(userVO.toUser());
			ServiceResult<UserResp> serviceResult = userService.login(req);
			if (serviceResult != null && serviceResult.isSuccess() && serviceResult.getBusinessObject() != null
					&& StringUtils.isNotBlank(serviceResult.getBusinessObject().getId() + "")) {
				int freeze = serviceResult.getBusinessObject().getUserFreeze();
				if (freeze == 0) {
					return new Result(false, "账户已被冻结");
				}
				if(serviceResult.getBusinessObject().getUserValidate() == 2){
					Long[] a = new Long[1];
					a[0] = serviceResult.getBusinessObject().getId();
					req.setIds(a);
					userService.delete(req);
					return new Result(false, "账户审核不通过");
				}
				if(serviceResult.getBusinessObject().getUserValidate() == 0){
					return new Result(false, "账户未审核");
				}
				res = new Result(true, "登录成功");
				setSession(serviceResult.getBusinessObject());
				setCookie(serviceResult.getBusinessObject(), remember, response);
			} else {
				res = new Result(false, "用户名或密码错误");
			}
		} catch (Exception e) {
			throw new MyException("登录错误", e);
		}

		return res;
	}

	private void setSession(UserResp userResp) throws MyException {
		SessionUser user = new SessionUser();
		user.setId(userResp.getId());
		user.setUserName(userResp.getUserName());
		user.setPassword(userResp.getPassword());
		user.setUserAge(userResp.getUserAge());
		user.setUserSex(userResp.getUserSex());
		user.setUserPhone(userResp.getUserPhone());
		user.setUserFreeze(userResp.getUserFreeze());
		user.setUserValidate(userResp.getUserValidate());
		user.setCreateTime(userResp.getCreateTime());
		user.setCreateUser(userResp.getCreateUser());
		user.setModifyTime(userResp.getModifyTime());
		user.setParentId(userResp.getParentId());

		if (userResp.getParentId() == 0) {
			if(userResp.getId() != 0){
				user.setCustId(userResp.getId());
			}
		} else {
			user.setCustId(userResp.getParentId());
		}

		SysPermissionReq d = new SysPermissionReq();
		d.setType("menu");
		d.setParentid(0L);
		d.setSortName("sortstring");
		d.setOrder("desc");
		d.setAvailable("0");
		if ("admin".equals(userResp.getUserName())) {
		
//			ServiceResult<BaseList<SysPermissionResp>> serviceResult = permissionService.findSysPermissionMenuList(d);
			ServiceResult<BaseList<SysPermissionResp>> serviceResult = permissionService.findUserSysPermissionList(d);
			user.setUrls(JSONArray.fromObject(serviceResult.getBusinessObject().getList()));
		} else {
			Long[] roleIdsArray = findUserRoles(userResp.getId());
			if(roleIdsArray.length == 0){
				throw new MyException("该用户尚未赋予角色");
			}
			user.setRoleIds(roleIdsArray);
			d.setRoleIds(roleIdsArray);
			d.setIsShow("0");
			ServiceResult<BaseList<SysPermissionResp>> serviceResult = permissionService.findUserSysPermissionList(d);
			user.setUrls(JSONArray.fromObject(serviceResult.getBusinessObject().getList()));

			SysPermissionReq sysPermissionReq = new SysPermissionReq();
			sysPermissionReq.setRoleIds(roleIdsArray);
			sysPermissionReq.setAvailable("0");
			ServiceResult<BaseList<SysPermissionResp>> permissionResult = permissionService
					.findSysPermissionList(sysPermissionReq);
			if (permissionResult.isSuccess()) {
				user.setPermissionList(permissionResult.getBusinessObject().getList());
			}
		}
		getSession().setAttribute(Constant.ENVIRONMENT_USER, user);
	}

	private Long[] findUserRoles(Long userId) {
		SysUserRoleReq req = new SysUserRoleReq();
		req.setSysUserId(userId);
		ServiceResult<BaseList<SysUserRoleResp>> resp = userRoleService.findSysUserRoleList(req);
		String roleIds = "";
		if (resp.isSuccess()) {
			for (SysUserRoleResp r : resp.getBusinessObject().getList()) {
				roleIds += r.getSysRoleId() + ",";
			}
		}
		if (StringUtils.isNotBlank(roleIds)) {
			roleIds.substring(0, roleIds.length() - 1);
		}
		Long[] roleIdsArray = new Long[roleIds.split(",").length];
		int index = 0;
		for (String str : roleIds.split(",")) {
			roleIdsArray[index++] = Long.parseLong(str);
		}
		return roleIdsArray;
	}

	private void setCookie(UserResp user, String remember, HttpServletResponse response) {
		if ("true".equals(remember)) {
			Cookie rememberCookie = new Cookie("zdy_operator_login_remember", remember);
			Cookie nameCookie = new Cookie("zdy_operator_login_username", user.getUserName());
			Cookie pwdCookie = new Cookie("zdy_operator_login_password", user.getPassword());
			rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			nameCookie.setMaxAge(60 * 60 * 24 * 30);
			pwdCookie.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(rememberCookie);
			response.addCookie(nameCookie);
			response.addCookie(pwdCookie);
		} else {
			Cookie rememberCookie = new Cookie("zdy_operator_login_remember", "false");
			rememberCookie.setMaxAge(60 * 60 * 24 * 30);
			response.addCookie(rememberCookie);
		}
	}
	
	
}
