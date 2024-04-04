package com.control.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.backend.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, String> {

	Application findByApplicationName(String applicationName);

	List<Application> findByApplicationNameIgnoreCaseContaining(String applicationName);

	List<Application> findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(
			String applicationName, String applicationEmail);

	@Query("SELECT applicationTokenExpiration FROM Application application WHERE (applicationId = ?1) AND (applicationIsEnabled = true)")
	Integer findByapplicationTokenExpiration(String applicationId);

}
