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
import com.control.model.dto.ApplicationPostDTO;
import com.control.model.dto.ApplicationPutDTO;
import com.control.repository.ApplicationRepository;

@Service
public class ApplicationService {

	@Autowired
	private ApplicationRepository applicationRepository;

	public boolean existsById(String applicationId) {
		return applicationRepository.existsById(applicationId);
	}

	public Page<Application> findAll(Pageable pageable) {
		return applicationRepository.findAll(pageable);
	}

	public Optional<Application> findById(String applicationId) {
		return applicationRepository.findById(applicationId);
	}

	public List<Application> findByNameContaining(String applicationName) {
		return applicationRepository.findByApplicationNameIgnoreCaseContaining(applicationName);
	}

	public List<Application> findByEmailContaining(String applicationEmail) {
		return applicationRepository.findByApplicationEmailIgnoreCaseContaining(applicationEmail);
	}

	public List<Application> find(String find) {
		return applicationRepository
				.findByApplicationNameIgnoreCaseContainingOrApplicationEmailIgnoreCaseContaining(find, find);
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ApplicationPostDTO applicationInsertDTO) {
		final var entity = new Application();
		BeanUtils.copyProperties(applicationInsertDTO, entity);
		applicationRepository.save(entity);
		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ApplicationPutDTO applicationUpdateDTO) {
		final var entity = new Application();
		BeanUtils.copyProperties(applicationUpdateDTO, entity);
		applicationRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ApplicationIdDTO applicationDTO) {
		applicationRepository.deleteById(applicationDTO.getApplicationId());
	}

}
