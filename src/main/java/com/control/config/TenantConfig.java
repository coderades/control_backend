package com.control.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.control.provider.TenantDataSourceProvider;
import com.control.service.ApplicationTenantService;

@Configuration
public class TenantConfig {

	private final ApplicationTenantService applicationTenantService;

	public TenantConfig(ApplicationTenantService applicationTenantService) {
		this.applicationTenantService = applicationTenantService;
	}

	@Bean
	public void tenantLoad() {
		applicationTenantService.findAll().forEach(TenantDataSourceProvider::addDataSource);
	}

}
