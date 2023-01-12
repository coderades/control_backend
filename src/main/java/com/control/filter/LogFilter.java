package com.control.filter;

import org.slf4j.MDC;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import jakarta.servlet.FilterChain;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class LogFilter extends OncePerRequestFilter {

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) {
		try {
			MDC.put("sessionId", request.getSession().getId());
			log.info("Request: {}={}", request.getMethod(), request.getRequestURI());
			filterChain.doFilter(request, response);
			log.info("Response: {}", response.getContentType());
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			MDC.remove("sessionId");
		}
	}

}