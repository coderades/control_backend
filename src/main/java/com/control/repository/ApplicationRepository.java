package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.control.model.Application;

@Repository
public interface ApplicationRepository extends JpaRepository<Application, String> {
		
	List<Application> findByApplicationNameIgnoreCaseContaining(String applicationName);

	List<Application> findByApplicationEmailIgnoreCaseContaining(String applicationEmail);
	
	List<Application> findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(String applicationName, String applicationEmail);
			
}
