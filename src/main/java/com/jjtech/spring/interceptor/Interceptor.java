package com.jjtech.spring.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.jjtech.spring.datasource.TenantDatabaseContex;
import com.jjtech.spring.datasource.TenantDatabaseContextHolder;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Component
public class Interceptor implements HandlerInterceptor {

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		log.info("Request intercepted preHandle: " + request.getHeader("clientgroup"));
		if (StringUtils.hasText(request.getHeader("clientgroup"))
				&& StringUtils.hasText(request.getHeader("tenantId"))) {
			TenantDatabaseContextHolder
					.set(new TenantDatabaseContex(request.getHeader("clientgroup"), request.getHeader("tenantId")));
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		log.info("Request intercepted postHandle: " + request.getHeader("clientgroup"));
		TenantDatabaseContextHolder.clear();
		HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		log.info("Request intercepted afterCompletion: " + request.getHeader("clientgroup"));
		HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
	}

}