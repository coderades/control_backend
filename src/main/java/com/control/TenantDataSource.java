package com.control;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.control.context.TenantContext;

public class TenantDataSource extends AbstractRoutingDataSource {

    @Override
    protected String determineCurrentLookupKey() {
        return TenantContext.getCurrentTenant();
    }
    
}