package com.control.tenant;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class TenantRouting extends AbstractRoutingDataSource {

	@Override
	protected String determineCurrentLookupKey() {
		return TenantContext.getCurrentTenant();
	}

}