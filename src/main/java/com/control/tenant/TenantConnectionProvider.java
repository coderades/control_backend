package com.control.tenant;

import javax.sql.DataSource;

import org.hibernate.engine.jdbc.connections.spi.AbstractDataSourceBasedMultiTenantConnectionProviderImpl;

public class TenantConnectionProvider extends AbstractDataSourceBasedMultiTenantConnectionProviderImpl {

	private static final long serialVersionUID = -7659357315355003531L;

	@Override
	protected DataSource selectAnyDataSource() {
		return TenantDataSourceProvider.getTenantDataSource("Default");
	}

	@Override
	protected DataSource selectDataSource(String tenantIdentifier) {
		return TenantDataSourceProvider.getTenantDataSource(tenantIdentifier);
	}

}
