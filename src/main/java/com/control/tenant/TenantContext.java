package com.control.tenant;

public class TenantContext {

	private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

	private TenantContext() {
		throw new IllegalStateException("Utility class");
	}

	public static String getCurrentTenant() {
		return CURRENT_TENANT.get();
	}

	public static void setCurrentTenant(String tenant) {
		CURRENT_TENANT.set(tenant);
	}

}
