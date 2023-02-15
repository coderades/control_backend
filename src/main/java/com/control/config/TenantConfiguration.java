package com.control.config;

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

import com.control.TenantDataSource;

@Configuration
public class TenantConfiguration {

	@Value("tenant_1")
	private String defaultTenant;

	@Bean
	@ConfigurationProperties(prefix = "tenants")
	DataSource dataSource() {
		final var files = Paths.get("tenant").toFile().listFiles();
		final Map<Object, Object> resolvedDataSources = new HashMap<>();

		for (var propertyFile : files) {
			try {
				final var tenantProperties = new Properties();
				tenantProperties.load(new FileInputStream(propertyFile));
				
				final var dataSourceBuilder = DataSourceBuilder.create();
				dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
				dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
				resolvedDataSources.put(tenantProperties.getProperty("name"), dataSourceBuilder.build());
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
