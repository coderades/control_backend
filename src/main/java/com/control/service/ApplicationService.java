package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Application;
import com.control.model.dto.ApplicationIdDTO;
import com.control.model.dto.ApplicationInsertDTO;
import com.control.model.dto.ApplicationUpdateDTO;
import com.control.repository.ApplicationRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	private final ApplicationIdDTO applicationIdDTO = new ApplicationIdDTO();

	public boolean existsById(String applicationId) {
		final var entity = applicationRepository.existsById(applicationId);

		log.info("Return: object={}", entity);

		return entity;
	}

	public Page<Application> findAll(Pageable pageable) {
		final var entity = applicationRepository.findAll(pageable);

		log.info("Return: elements={}, object={}", entity.getSize(), entity);

		return entity;
	}

	public Optional<Application> findById(String applicationId) {
		final var entity = applicationRepository.findById(applicationId);

		log.info("Return: object={}", entity);

		return entity;
	}

	public List<Application> findByNameContaining(String applicationName) {
		final var entity = applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);

		log.info("Return: elements={}, object={}", entity.size(), entity);

		return entity;
	}

	public List<Application> findByEmailContaining(String applicationEmail) {
		final var entity = applicationRepository.findByApplicationEmailIgnoreCaseContaining(applicationEmail);

		log.info("Return: object={}", entity);

		return entity;
	}

	public List<Application> find(String find) {
		final var entity = applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);

		log.info("Return: elements={}, object={}", entity.size(), entity);

		return entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public ApplicationIdDTO save(ApplicationInsertDTO applicationInsertDTO) {
		final var entity = new Application();

		BeanUtils.copyProperties(applicationInsertDTO, entity);

		applicationRepository.save(entity);
		applicationIdDTO.setApplicationId(entity.getApplicationId());
		log.info("Return: applicationId={}", entity.getApplicationId());

		return applicationIdDTO;
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationUpdateDTO applicationUpdateDTO) {
		final var entity = new Application();

		BeanUtils.copyProperties(applicationUpdateDTO, entity);

		applicationRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ApplicationIdDTO applicationDTO) {
		applicationRepository.deleteById(applicationDTO.getApplicationId());
		log.info("Status: OK");
	}

}
