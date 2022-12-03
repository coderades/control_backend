package com.control.tenant;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.control.service.ApplicationTenantService;

@Configuration
public class TenantConfig {

	private final ApplicationTenantService applicationTenantService;

	public TenantConfig(ApplicationTenantService applicationTenantService) {
		this.applicationTenantService = applicationTenantService;
	}

	@Bean
	public void tenantLoad() {
		final var entity = applicationTenantService.findAll();	
		for (var propertyFile : entity) {
			TenantDataSourceProvider.addDataSource(propertyFile);
		}
	}
	
}
