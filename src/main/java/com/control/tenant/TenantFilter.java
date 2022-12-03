package com.control.tenant;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import com.control.controller.ApplicationTenantController;

import lombok.extern.slf4j.Slf4j;

@Component
@Order(1)
public class TenantFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) {
		final var httpServletRequest = (HttpServletRequest) servletRequest;
		final var tenantId = httpServletRequest.getHeader("X-TenantID");
		final var tenantConnectionProvider = new TenantConnectionProvider();

		try {
			tenantConnectionProvider.selectDataSource(tenantId);
			filterChain.doFilter(servletRequest, servletResponse);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
		}
	}

}
