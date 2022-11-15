package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.control.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
	
	Application findByApplicationName(String applicationName);
		
	List<?> findByApplicationNameIgnoreCaseContaining(String applicationName);

	List<?> findByApplicationEmailIgnoreCaseContaining(String applicationEmail);
	
	List<?> findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(String applicationName, String applicationEmail);
		
	@Query("SELECT application.applicationSecret FROM Application application WHERE (applicationIsEnabled = 1)")
	String findApplicationSecret(String applicationId);
	
}
