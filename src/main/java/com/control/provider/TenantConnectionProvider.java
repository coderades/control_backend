package com.control.provider;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

public class TenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = -7659357315355003531L;

	@Override
	public DataSource selectAnyDataSource() {
		return TenantDataSourceProvider.getTenantDataSource("Default");
	}

	@Override
	public DataSource selectDataSource(String tenantIdentifier) {
		return TenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
	}

}
