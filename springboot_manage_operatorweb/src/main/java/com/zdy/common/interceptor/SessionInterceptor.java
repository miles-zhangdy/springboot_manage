package com.zdy.common.interceptor;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.zdy.biz.syspermission.dto.SysPermissionResp;
import com.zdy.common.Constant;
import com.zdy.common.model.SessionUser;
import com.zdy.exception.MyException;

public class SessionInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object arg2, ModelAndView arg3)
			throws Exception {

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(Constant.ENVIRONMENT_USER);
		/* 登录判断 */
		if (sessionUser == null || StringUtils.isBlank(sessionUser.getId() + "")) {
			if (isAjax(request)) {
				response.setHeader("sessionstatus", "timeout");
				return false;
			}
			response.sendRedirect(request.getContextPath() + "/user/tologin");
			return false;
		}
		/* 权限判断 */
		if("admin".equals(sessionUser.getUserName())){
			return true;
		}
		String url = request.getServletPath();
		List<SysPermissionResp> permissionList = sessionUser.getPermissionList();
		for (SysPermissionResp permission : permissionList) {
		//	System.out.println("url:"+ url + "####" + permission.getUrl() + " checked : " + permission.getChecked());
			if (url.indexOf(permission.getUrl()) > -1 && StringUtils.isNotBlank(permission.getUrl())) {
				if("false".equals(permission.getChecked())){
					throw new MyException("对不起，您没有【"+permission.getName()+"】的权限");
				}
				return true;
			}
		}
		return true;

	}

	boolean isAjax(HttpServletRequest request) {
		return (request.getHeader("X-Requested-With") != null
				&& "XMLHttpRequest".equals(request.getHeader("X-Requested-With").toString()));
	}
}
