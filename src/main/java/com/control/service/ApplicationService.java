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
		final var entity = applicationRepository.findAll(pageable);

		log.info("Elements: {}, Object: {}", entity.getSize(), entity.getContent());

		return entity;
	}

	public Application findById(String applicationId) {
		final var entity = applicationRepository.findById(applicationId);

		log.info("Object: {}", entity);

		return entity.isPresent() ? entity.get() : null;
	}

	public Application findByName(String applicationName) {
		final var entity = applicationRepository.findByApplicationName(applicationName);

		log.info("Object: {}", entity);

		return entity == null ? null : entity;
	}

	public List<?> findByNameContaining(String applicationName) {
		final var entity = applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);

		log.info("Elements: {}, Object: {}", entity.size(), entity);

		return entity == null ? null : entity;
	}

	public List<?> findByEmailContaining(String applicationEmail) {
		final var entity = applicationRepository.findByApplicationEmailIgnoreCaseContaining(applicationEmail);

		log.info("Object: {}", entity);

		return entity == null ? null : entity;
	}

	public List<?> find(String find) {
		final var entity = applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);

		log.info("Elements: {}, Object: {}", entity.size(), entity);

		return entity == null ? null : entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ApplicationInsertDTO applicationInsertDTO) {
		final var entity = new Application();

		BeanUtils.copyProperties(applicationInsertDTO, entity);

		applicationRepository.save(entity);
		log.info("Return: applicationId={}", entity.getApplicationId());

		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationUpdateDTO applicationUpdateDTO) {
		final var entity = new Application();

		BeanUtils.copyProperties(applicationUpdateDTO, entity);

		applicationRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String applicationId) {
		applicationRepository.deleteById(applicationId);
		log.info("Status: OK");
	}

}
