package com.control.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.control.model.ApplicationTenant;
import com.control.repository.ApplicationTenantRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationTenantService {

	private final ApplicationTenantRepository applicationTenantRepository;
	
	public ApplicationTenantService(ApplicationTenantRepository applicationTenantRepository) {
		this.applicationTenantRepository = applicationTenantRepository;
	}
	
	public List<ApplicationTenant> findAll() {
		final var entity = applicationTenantRepository.findAll();

		log.info("Elements: {}, Object: {}", entity.size(), entity);

		return entity == null ? null : entity;
	}
	
	
	public List<?> findByDataSource() {
		final var entity = applicationTenantRepository.findByDataSource();

		log.info("Elements: {}, Object: {}", entity.size(), entity);

		return entity == null ? null : entity;
	}
	
}
