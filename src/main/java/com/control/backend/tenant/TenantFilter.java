package com.control.backend.tenant;

import java.io.IOException;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

@Component
@Order(1)
class TenantFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
			throws IOException, ServletException {
		final var request = (HttpServletRequest) servletRequest;
		final var tenantId = request.getHeader("TenantID");
		
		try {			
			TenantContext.setCurrentTenant(tenantId);
			filterChain.doFilter(servletRequest, servletResponse);
		} finally {
			TenantContext.setCurrentTenant("");
		}
	}
}