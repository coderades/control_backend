package com.control.backend.tenant;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TenantConfig {

	@Value("${api.security.default.tenant}")
	private String defaultTenant;

	@Bean
	@ConfigurationProperties(prefix = "tenants")
	DataSource dataSource() {
		final var files = Paths.get("src/main/resources/tenants").toFile().listFiles();
		final Map<Object, Object> resolvedDataSources = new HashMap<>();

		for (var propertyFile : files) {
			final var dataSourceBuilder = DataSourceBuilder.create();
			try {
				final var tenantProperties = new Properties();
				tenantProperties.load(new FileInputStream(propertyFile));
				dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
				dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
				resolvedDataSources.put(tenantProperties.getProperty("datasource.tenant-name"),
						dataSourceBuilder.build());
			} catch (IOException exp) {
				throw new RuntimeException("Problem in tenant datasource:" + exp);
			}
		}

		final var dataSource = new TenantDataSource();
		dataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenant));
		dataSource.setTargetDataSources(resolvedDataSources);
		dataSource.afterPropertiesSet();

		return dataSource;
	}

}