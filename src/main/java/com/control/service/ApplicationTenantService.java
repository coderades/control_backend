package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.ApplicationTenant;
import com.control.model.dto.ApplicationTenantDTO;
import com.control.model.dto.ApplicationTenantInsertDTO;
import com.control.model.dto.ApplicationTenantUpdateDTO;
import com.control.repository.ApplicationTenantRepository;

@Service
public class ApplicationTenantService {

	@Autowired
	private ApplicationTenantRepository applicationTenantRepository;

	public Page<ApplicationTenant> findAll(Pageable pageable) {
		return applicationTenantRepository.findAll(pageable);
	}

	public List<ApplicationTenant> findAll() {
		return applicationTenantRepository.findAll();
	}

	public Optional<ApplicationTenant> findById(String applicationTenantId) {
		return applicationTenantRepository.findById(applicationTenantId);
	}

	public List<ApplicationTenant> findByDataSource() {
		return applicationTenantRepository.findByDataSource();
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ApplicationTenantInsertDTO applicationTenantInsertDTO) {
		final var entity = new ApplicationTenant();
		BeanUtils.copyProperties(applicationTenantInsertDTO, entity);
		applicationTenantRepository.save(entity);
		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationTenantUpdateDTO applicationTenantUpdateDTO) {
		final var entity = new ApplicationTenant();
		BeanUtils.copyProperties(applicationTenantUpdateDTO, entity);
		applicationTenantRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ApplicationTenantDTO applicationTenantIdDTO) {
		applicationTenantRepository.deleteById(applicationTenantIdDTO.getApplicationTenantId());
	}

}
