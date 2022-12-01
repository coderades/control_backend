package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.model.ApplicationTenant;

public interface ApplicationTenantRepository extends JpaRepository<ApplicationTenant, Integer> {
	
	@Query("SELECT applicationTenant FROM ApplicationTenant applicationTenant INNER JOIN Application application ON applicationTenant.applicationId = application.applicationId WHERE (applicationTenant.applicationTenantIsEnabled = 1) AND (application.applicationIsEnabled = 1)")
	List<?> findByDataSource();
	
}