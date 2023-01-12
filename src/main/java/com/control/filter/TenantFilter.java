package com.control.filter;

import java.io.IOException;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.control.provider.TenantConnectionProvider;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
@Slf4j
public class TenantFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		final var httpServletRequest = (HttpServletRequest) servletRequest;
		final var tenantId = httpServletRequest.getHeader("X-TenantID");
		final var tenantConnectionProvider = new TenantConnectionProvider();

		try {
			MDC.put("sessionId", httpServletRequest.getSession().getId());
			log.info("Tenant: tenantId={}", tenantId);
			tenantConnectionProvider.selectDataSource(tenantId);
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (IOException | ServletException e) {
			log.error("Tenant: {}", e);
		}
	}

}
