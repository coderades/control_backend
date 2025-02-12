package com.control.backend.auth.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.Resource;
import com.control.backend.auth.model.dto.ResourceDTO;
import com.control.backend.auth.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourseRepository;

	public Page<Resource> findAll(Pageable pageable) {
		return resourseRepository.findAll(pageable);
	}

	public Optional<Resource> findById(Long resourceId) {
		return resourseRepository.findById(resourceId);
	}

	public Resource findByName(String resourseName) {
		return resourseRepository.findByResourceName(resourseName);
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(ResourceDTO resourceDTO) {
		final var resource = new Resource();
		BeanUtils.copyProperties(resourceDTO, resource);
		return resourseRepository.save(resource).getResourceId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long resourceId, ResourceDTO resourceDTO) {
		final var resource = new Resource();
		BeanUtils.copyProperties(resourceDTO, resource);
		resource.setResourceId(resourceId);
		resourseRepository.save(resource);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long resourceId) {
		resourseRepository.deleteById(resourceId);
	}

}
