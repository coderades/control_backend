package com.control.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.model.Application;
import com.control.backend.model.dto.ApplicationIdDTO;
import com.control.backend.model.dto.ApplicationInsertDTO;
import com.control.backend.model.dto.ApplicationTokenExpirationDTO;
import com.control.backend.model.dto.ApplicationUpdateDTO;
import com.control.backend.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public Page<Application> findAll(Pageable pageable) {
		final var application = applicationRepository.findAll(pageable);
		return application;
	}

	public Optional<Application> findById(String applicationId) {
		final var application = applicationRepository.findById(applicationId);
		return application;
	}

	public Application findByName(String applicationName) {
		final var application = applicationRepository.findByApplicationName(applicationName);
		return application;
	}

	public List<Application> findByNameContaining(String applicationName) {
		final var application = applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);
		return application;
	}

	public List<Application> find(String find) {
		final var application = applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);
		return application;
	}

	public ApplicationTokenExpirationDTO findByApplicationTokenExpiration(String applicationId) {
		return new ApplicationTokenExpirationDTO(applicationRepository.findByapplicationTokenExpiration(applicationId));
	}

	@Transactional(rollbackFor = Exception.class)
	public ApplicationIdDTO save(ApplicationInsertDTO applicationInsertDTO) {
		final var application = new Application();
		BeanUtils.copyProperties(applicationInsertDTO, application);
		return new ApplicationIdDTO(applicationRepository.save(application).getApplicationId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationUpdateDTO applicationUpdateDTO) {
		final var application = new Application();
		BeanUtils.copyProperties(applicationUpdateDTO, application);
		applicationRepository.save(application);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String applicationId) {
		applicationRepository.deleteById(applicationId);
	}

}
