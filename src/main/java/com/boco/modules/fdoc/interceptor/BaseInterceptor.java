package com.boco.modules.fdoc.interceptor;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 * 基础拦截器类
 * @author q
 *
 */
public abstract class BaseInterceptor extends HandlerInterceptorAdapter {
	public String success;
	public String failed;

	public boolean isMyHandler(Object handler) {
		if (!(handler instanceof HandlerMethod))
			return false;
		HandlerMethod handlerMethod = (HandlerMethod) handler;
		Interceptor interceptor = handlerMethod
				.getMethodAnnotation(Interceptor.class);
		if (interceptor == null)
			return false;
		if (!interceptor.name().equals(this.getClass().getSimpleName())
				&& !interceptor.name().equals(this.getClass().getName()))
			return false;
		success = interceptor.success();
		failed = interceptor.failed();
		return true;
	}

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		if (isMyHandler(handler)) {
			return runHandler(response, runHandler(request, response));
		}
		return super.preHandle(request, response, handler);
	}

	public abstract boolean runHandler(HttpServletRequest request,
			HttpServletResponse response);

	/**
	 * 运行结果
	 * 
	 * @param response
	 * @param isInterceptor
	 *            是否拦截,true拦截,跳转向failed指向页面,false:不拦截,跳转向success指向页面;
	 *            success和failed为空时不做任何操作
	 * @return
	 * @throws Exception
	 */
	public boolean runHandler(HttpServletResponse response,
			boolean isInterceptor) throws Exception {
		if (!isInterceptor) {
			if (!success.equals("")) {
				response.sendRedirect(success);
				return false;
			}
		} else {
			if (!failed.equals("")) {
				response.sendRedirect(failed);
				return false;
			}
		}
		return true;
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public static @interface Interceptor {
		public String name();

		public String success() default "";

		public String failed() default "";
	}
}
