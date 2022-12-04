package com.control.resolver;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

import com.control.provider.TenantDataSourceProvider;

public class TenantIdentifierResolver implements CurrentTenantIdentifierResolver {

	@Override
	public String resolveCurrentTenantIdentifier() {
		if (!"".equals(TenantDataSourceProvider.TenantId)) {
			return TenantDataSourceProvider.TenantId;
		}
		return "Default";
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return true;
	}
}
