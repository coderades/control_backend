package com.control.backend.auth.log;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MethodInterceptor implements HandlerInterceptor {

	private static final Logger logger = LoggerFactory.getLogger(MethodInterceptor.class);

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		final var jsonObject = new JSONObject();
		try {
			jsonObject.put("method",request.getMethod());
			jsonObject.put("link", request.getRequestURI());
			logger.info("{} | HTTPSTATUS: {} | SESSION: {} | ACTION: {}", LocalDateTime.now(), "INFO", RequestContextHolder.currentRequestAttributes().getSessionId(), jsonObject.toString().replace("\\", ""));
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// post-processing logic
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// after-completion processing logic
	}

}