package com.control.backend.component;

import java.io.IOException;

import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
public class LogFilter implements Filter {
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
//		final var principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
//		
//		MDC.put("userId", principal.getUserId());
//		MDC.put("sessionId", ((HttpServletRequest) request).getSession().getId());
//		MDC.clear();

		chain.doFilter(request, response);
	}

}
