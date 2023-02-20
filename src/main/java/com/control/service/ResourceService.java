package com.control.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Resource;
import com.control.model.dto.ResourceDTO;
import com.control.model.dto.ResourceInsertDTO;
import com.control.model.dto.ResourceUpdadeDTO;
import com.control.repository.ResourceRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;
	
	public Boolean existsById(String roleId) {
		final var entity = resourceRepository.existsById(roleId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Page<Resource> findAll(Pageable pageable) {
		final var entity = resourceRepository.findAll(pageable);

		log.info("Return: Elements={}, Object={}", entity.getSize(), entity);

		return entity;
	}

	public Optional<Resource> findById(String resourceId) {
		final var entity = resourceRepository.findById(resourceId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ResourceInsertDTO resourceInsertDTO) {
		final var entity = new Resource();

		BeanUtils.copyProperties(resourceInsertDTO, entity);

		resourceRepository.save(entity);
		log.info("Return: applicationId={}", entity.getApplicationId());

		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ResourceUpdadeDTO resourceUpdateDTO) {
		final var entity = new Resource();

		BeanUtils.copyProperties(resourceUpdateDTO, entity);

		resourceRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ResourceDTO resourceDTO) {
		resourceRepository.deleteById(resourceDTO.getResourceId());
		log.info("Status: OK");
	}

}
