package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.model.Application;

public interface ApplicationRepository extends JpaRepository<Application, String> {

	Boolean existsByApplicationName(String applicationName);
	
	Boolean existsByApplicationIdIsNotAndApplicationName(String applicationId, String applicationName);
		
	List<Application> findByApplicationNameIgnoreCaseContaining(String applicationName);

	List<Application> findByApplicationEmailIgnoreCaseContaining(String applicationEmail);
	
	List<Application> findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(String applicationName, String applicationEmail);
			
}
