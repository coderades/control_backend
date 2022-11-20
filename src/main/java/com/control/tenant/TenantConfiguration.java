package com.control.tenant;

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
public class TenantConfiguration {

	@Value("tenant_1")
	private String defaultTenant;

	@Bean
	@ConfigurationProperties(prefix = "tenant")
	public DataSource dataSource() {

		final var files = Paths.get("src/main/resources/tenants").toFile().listFiles();
		final Map<Object, Object> resolvedDataSources = new HashMap<>();

		for (var propertyFile : files) {
			final var tenantProperties = new Properties();
			final var dataSourceBuilder = DataSourceBuilder.create();

			try {
				tenantProperties.load(new FileInputStream(propertyFile));
				
				dataSourceBuilder.driverClassName(tenantProperties.getProperty("datasource.driver-class-name"));
				dataSourceBuilder.username(tenantProperties.getProperty("datasource.username"));
				dataSourceBuilder.password(tenantProperties.getProperty("datasource.password"));
				dataSourceBuilder.url(tenantProperties.getProperty("datasource.url"));
				
				resolvedDataSources.put(tenantProperties.getProperty("name"), dataSourceBuilder.build());
			} catch (IOException e) {
				throw new RuntimeException("Problem in tenant datasource:" + e);
			}
		}

		final var dataSource = new TenantRouting();
		
		dataSource.setDefaultTargetDataSource(resolvedDataSources.get(defaultTenant));
		dataSource.setTargetDataSources(resolvedDataSources);
		dataSource.afterPropertiesSet();

		return dataSource;
	}

}
