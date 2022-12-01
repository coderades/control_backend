package com.control.tenant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

import com.control.model.ApplicationTenant;
import com.control.util.FileUtil;

public class TenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = -7659357315355003531L;

	@Override
	protected DataSource selectAnyDataSource() {
		System.out.println(31);

		final List<String> list = new ArrayList<>(Arrays.asList("spring.datasource.url", "spring.datasource.username",
				"spring.datasource.password", "spring.datasource.driver-class-name"));
		
		Map<String, String> map = new HashMap<>();
		map = FileUtil.read("src/main/resources/application.properties", "=", list);

		final var applicationTenant = new ApplicationTenant();
		applicationTenant.setApplicationTenantName("Default");
		applicationTenant.setApplicationTenantUrl(map.get("spring.datasource.url"));
		applicationTenant.setApplicationTenantUserName(map.get("spring.datasource.username"));
		applicationTenant.setApplicationTenantPassword(map.get("spring.datasource.password"));
		applicationTenant.setApplicationTenantDriverClassName(map.get("spring.datasource.driver-class-name"));

		TenantDataSourceProvider.addDataSource(applicationTenant);
		return TenantDataSourceProvider.getTenantDataSource("Default");
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		System.out.println(32);
		return TenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
	}

}
