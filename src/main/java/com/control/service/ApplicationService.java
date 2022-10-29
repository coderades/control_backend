package com.control.service;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Application;
import com.control.model.dto.ApplicationInsertDTO;
import com.control.model.dto.ApplicationUpdateDTO;
import com.control.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationService {

	private final ApplicationRepository applicationRepository;

	public ApplicationService(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	public Page<?> findAll(Pageable pageable) {
		final var application = applicationRepository.findAll(pageable);

		log.info("Elements: {}, Object: {}", application.getSize(), application.getContent());

		return application;
	}

	public Application findById(String applicationId) {
		final var application = applicationRepository.findById(applicationId);

		log.info("Object: {}", application);

		return application.isPresent() ? application.get() : null;
	}

	public Application findByName(String applicationName) {
		final var application = applicationRepository.findByApplicationName(applicationName);

		log.info("Object: {}", application);

		return application == null ? null : application;
	}

	public List<?> findByNameContaining(String applicationName) {
		final var application = applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);

		log.info("Elements: {}, Object: {}", application.size(), application);

		return application == null ? null : application;
	}

	public Application findByEmail(String applicationEmail) {
		final var application = applicationRepository.findByApplicationEmail(applicationEmail);

		log.info("Object: {}", application);

		return application.isPresent() ? application.get() : null;
	}

	public List<?> find(String find) {
		final var application = applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);

		log.info("Elements: {}, Object: {}", application.size(), application);

		return application == null ? null : application;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ApplicationInsertDTO applicationInsertDTO) {
		final var application = new Application();

		BeanUtils.copyProperties(applicationInsertDTO, application);
		
		applicationRepository.save(application);
		log.info("Return: applicationId={}", application.getApplicationId());

		return application.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationUpdateDTO applicationUpdateDTO) {
		final var application = new Application();

		BeanUtils.copyProperties(applicationUpdateDTO, application);
		
		applicationRepository.save(application);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String applicationId) {
		applicationRepository.deleteById(applicationId);
		log.info("Status: OK");
	}

}
