package com.control.tenant;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.boot.jdbc.DataSourceBuilder;

import com.control.model.ApplicationTenant;

/**
 * 这个类负责根据租户ID来提供对应的数据源
 * 
 * @author lanyuanxiaoyao
 * @version 1.0
 */
public class TenantDataSourceProvider {

	public static volatile String TenantId = "";
	private static final Map<String, DataSource> dataSourceMap = new HashMap<>();
	
	private TenantDataSourceProvider() {
		throw new IllegalStateException("Utility class");
	}
	
	public static DataSource getTenantDataSource(String tenantId) {
		TenantId = tenantId;
		System.out.println(4);
		if (dataSourceMap.containsKey(tenantId)) {
			return dataSourceMap.get(tenantId);
		} else {
			return dataSourceMap.get("Default");
		}
	}

	public static void addDataSource(ApplicationTenant applicationTenant) {
		final var dataSourceBuilder = DataSourceBuilder.create();
		dataSourceBuilder.url(applicationTenant.getApplicationTenantUrl());
		dataSourceBuilder.username(applicationTenant.getApplicationTenantUserName());
		dataSourceBuilder.password(applicationTenant.getApplicationTenantPassword());
		dataSourceBuilder.driverClassName(applicationTenant.getApplicationTenantDriverClassName());

		dataSourceMap.put(applicationTenant.getApplicationId() == null ?  applicationTenant.getApplicationTenantName() : applicationTenant.getApplicationId().replace("-", ""), dataSourceBuilder.build());
	}

}
