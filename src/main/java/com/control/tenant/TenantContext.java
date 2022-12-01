package com.control.tenant;

public class TenantContext {

	private static final ThreadLocal<String> CURRENT_TENANT = new ThreadLocal<>();

	private TenantContext() {
		throw new IllegalStateException("Utility class");
	}

	public static String getCurrentTenant() {
		System.out.println(2);
		return CURRENT_TENANT.get();
	}

	public static void setCurrentTenant(String tenant) {
		System.out.println(2);
		CURRENT_TENANT.set(tenant);
	}

}
