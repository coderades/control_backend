package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.ApplicationTenant;
import com.control.model.dto.ApplicationTenantDTO;
import com.control.model.dto.ApplicationTenantInsertDTO;
import com.control.model.dto.ApplicationTenantUpdateDTO;
import com.control.repository.ApplicationTenantRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationTenantService {

	private final ApplicationTenantRepository applicationTenantRepository;

	public ApplicationTenantService(ApplicationTenantRepository applicationTenantRepository) {
		this.applicationTenantRepository = applicationTenantRepository;
	}

	public Page<ApplicationTenant> findAll(Pageable pageable) {
		final var entity = applicationTenantRepository.findAll(pageable);

		log.info("Return: Elements={}, Object={}", entity.getSize(), entity);

		return entity;
	}
	
	public List<ApplicationTenant> findAll() {
		final var entity = applicationTenantRepository.findAll();

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	public Optional<ApplicationTenant> findById(String applicationTenantId) {
		final var entity = applicationTenantRepository.findById(applicationTenantId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public List<ApplicationTenant> findByDataSource() {
		final var entity = applicationTenantRepository.findByDataSource();

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ApplicationTenantInsertDTO applicationTenantInsertDTO) {
		final var entity = new ApplicationTenant();

		BeanUtils.copyProperties(applicationTenantInsertDTO, entity);

		applicationTenantRepository.save(entity);
		log.info("Return: applicationTenantId={}", entity.getApplicationTenantId());
		
		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationTenantUpdateDTO applicationTenantUpdateDTO) {
		final var entity = new ApplicationTenant();

		BeanUtils.copyProperties(applicationTenantUpdateDTO, entity);

		applicationTenantRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ApplicationTenantDTO applicationTenantIdDTO) {
		applicationTenantRepository.deleteById(applicationTenantIdDTO.getApplicationTenantId());
		log.info("Status: OK");
	}

}
