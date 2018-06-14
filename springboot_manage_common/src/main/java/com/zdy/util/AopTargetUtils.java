package com.zdy.util;

import java.lang.reflect.Field;

import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;

public class AopTargetUtils {

	public static Object getTarget(Object proxy) {

		if (!AopUtils.isAopProxy(proxy)) {
			return proxy;// 不是代理对象
		}

		if (AopUtils.isJdkDynamicProxy(proxy)) {
			return getJdkDynamicProxyTargetObject(proxy);
		} else { // cglib
			return getCglibProxyTargetObject(proxy);
		}

	}

	private static Object getCglibProxyTargetObject(Object proxy) {
		Object target = null;
		try {
			
			Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
			h.setAccessible(true);
			Object dynamicAdvisedInterceptor = h.get(proxy);
			
			Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
			advised.setAccessible(true);
			
			target = ((AdvisedSupport) advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return target;
	}

	private static Object getJdkDynamicProxyTargetObject(Object proxy)  {
		Object target = null;
		try {
			
			Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
			h.setAccessible(true);
			AopProxy aopProxy = (AopProxy) h.get(proxy);
			
			Field advised = aopProxy.getClass().getDeclaredField("advised");
			advised.setAccessible(true);
			
			target = ((AdvisedSupport) advised.get(aopProxy)).getTargetSource().getTarget();
		} catch (Exception e) {
			e.printStackTrace();
		}

		return target;
	}
}
