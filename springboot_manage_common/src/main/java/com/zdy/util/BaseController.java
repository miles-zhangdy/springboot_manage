package com.zdy.util;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.context.request.ServletWebRequest;

public class BaseController {

	public String getCtx() {
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes())
				.getRequest();
		return request.getContextPath();
	}

	public HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public HttpServletResponse getResponse() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
	}

	public HttpSession getSession() {
		return getRequest().getSession();
	}

	public static void transMap2Bean(Object bean, Map<String, Object> map) {
		if (null == bean || null == map) {
			return;
		}
		try {
			PropertyDescriptor[] propertyDescriptors = propertyDescriptor(bean);
			for (PropertyDescriptor property : propertyDescriptors) {
				String key = property.getName();
				if (map.containsKey(key)) {
					Object value = map.get(key);
					Method setter = property.getWriteMethod();
					if (null != setter) {
						setter.invoke(bean, value);
					}
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static PropertyDescriptor[] propertyDescriptor(Object obj) throws IntrospectionException {
		BeanInfo beanInfo = Introspector.getBeanInfo(obj.getClass());
		return beanInfo.getPropertyDescriptors();
	}
}
