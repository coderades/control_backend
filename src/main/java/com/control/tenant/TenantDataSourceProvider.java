package com.control.tenant;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.control.model.ApplicationTenant;
import com.control.util.FileUtil;

import lombok.extern.slf4j.Slf4j;

/**
 * @see https://github.com/lanyuanxiaoyao/multi-tenant
 */
@Slf4j
public class TenantDataSourceProvider {

	public static volatile String TenantId = "";
	private static final Map<String, DataSource> dataSourceMap = new HashMap<>();

	static {
		final var map = new HashMap<String, String>(FileUtil.read("src/main/resources/application.properties", "=",
				new ArrayList<>(Arrays.asList("datasource.url", "datasource.username", "datasource.password",
						"datasource.driver-class-name"))));

		final var dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(map.get("spring.datasource.url"));
		dataSourceBuilder.username(map.get("spring.datasource.username"));
		dataSourceBuilder.password(map.get("spring.datasource.password"));
		dataSourceBuilder.driverClassName(map.get("spring.datasource.driver-class-name"));

		dataSourceMap.put("Default", dataSourceBuilder.build());
	}

	private TenantDataSourceProvider() {
		throw new IllegalStateException("Utility class");
	}

	public static DataSource getTenantDataSource(String tenantId) {
		TenantId = Optional.ofNullable(tenantId).orElse("");

		if (dataSourceMap.containsKey(TenantId)) {
			log.info("Tenant: tenantId {} found", tenantId);
			return dataSourceMap.get(TenantId);
		} else {
			ResponseEntity.status(HttpStatus.FORBIDDEN).body(
					new StringBuilder().append("Tenant: tenantId ").append(tenantId).append(" not found").toString());
			log.warn("Tenant: tenantId {} not found", tenantId);
			return dataSourceMap.get("Default");
		}
	}

	public static void addDataSource(ApplicationTenant applicationTenant) {
		final var dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(applicationTenant.getApplicationTenantUrl());
		dataSourceBuilder.username(applicationTenant.getApplicationTenantUserName());
		dataSourceBuilder.password(applicationTenant.getApplicationTenantPassword());
		dataSourceBuilder.driverClassName(applicationTenant.getApplicationTenantDriverClassName());

		dataSourceMap
				.put(applicationTenant.getApplicationTenantId() == null ? applicationTenant.getApplicationTenantName()
						: applicationTenant.getApplicationTenantId().replace("-", ""), dataSourceBuilder.build());
	}

}
