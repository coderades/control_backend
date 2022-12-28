package com.control.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.control.provider.TenantConnectionProvider;

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
