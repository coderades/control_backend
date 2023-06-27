package com.control.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Resource;
import com.control.model.dto.ResourceIdDTO;
import com.control.model.dto.ResourceInsertDTO;
import com.control.model.dto.ResourceUpdadeDTO;
import com.control.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	public Page<Resource> findAll(Pageable pageable) {
		return resourceRepository.findAll(pageable);
	}

	public Optional<Resource> findById(String resourceId) {
		return resourceRepository.findById(resourceId);
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(ResourceInsertDTO resourceInsertDTO) {
		final var entity = new Resource();
		BeanUtils.copyProperties(resourceInsertDTO, entity);
		resourceRepository.save(entity);
		return entity.getApplicationId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ResourceUpdadeDTO resourceUpdateDTO) {
		final var entity = new Resource();
		BeanUtils.copyProperties(resourceUpdateDTO, entity);
		resourceRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(ResourceIdDTO resourceDTO) {
		resourceRepository.deleteById(resourceDTO.getResourceId());
	}

}
