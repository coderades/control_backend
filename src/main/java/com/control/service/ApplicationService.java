package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Application;
import com.control.model.dto.ApplicationDTO;
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

	public Page<Application> findAll(Pageable pageable) {
		final var entity = applicationRepository.findAll(pageable);

		log.info("Return: Elements={}, Object={}", entity.getSize(), entity);

		return entity;
	}

	public Optional<Application> findById(String applicationId) {
		final var entity = applicationRepository.findById(applicationId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public List<Application> findByNameContaining(String applicationName) {
		final var entity = applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
	}

	public List<Application> findByEmailContaining(String applicationEmail) {
		final var entity = applicationRepository.findByApplicationEmailIgnoreCaseContaining(applicationEmail);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public List<Application> find(String find) {
		final var entity = applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity;
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
	public void delete(ApplicationDTO applicationIdDTO) {
		applicationRepository.deleteById(applicationIdDTO.getApplicationId());
		log.info("Status: OK");
	}

}
