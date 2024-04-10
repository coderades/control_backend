package com.control.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.model.Resource;
import com.control.backend.model.dto.ResourceIdDTO;
import com.control.backend.model.dto.ResourceInsertDTO;
import com.control.backend.model.dto.ResourcePathDTO;
import com.control.backend.model.dto.ResourceUpdateDTO;
import com.control.backend.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	public Page<Resource> findAll(Pageable pageable) {
		final var resource = resourceRepository.findAll(pageable);
		return resource;
	}

	public Optional<Resource> findById(String resourceId) {
		final var resource = resourceRepository.findById(resourceId);
		return resource;
	}

	public Resource findByName(String resourceName) {
		final var resource = resourceRepository.findByResourceName(resourceName);
		return resource;
	}

	public List<ResourcePathDTO> findByResourcePath(String applicationKeyAccess, HttpMethod httpMethod,
			Boolean permissionIsPublic) {
		final var resource = resourceRepository.findByResourcePath(applicationKeyAccess, httpMethod.name(),
				permissionIsPublic);
		return resource;
	}

	public List<Resource> find(String find) {
		final var resource = resourceRepository
				.findByResourceNameIgnoreCaseContainingOrResourcePathIgnoreCaseContaining(find, find);
		return resource;
	}

	@Transactional(rollbackFor = Exception.class)
	public ResourceIdDTO save(ResourceInsertDTO resourceInsertDTO) {
		final var resource = new Resource();
		BeanUtils.copyProperties(resourceInsertDTO, resource);
		return new ResourceIdDTO(resourceRepository.save(resource).getResourceId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(ResourceUpdateDTO resourceUpdateDTO) {
		final var resource = new Resource();
		BeanUtils.copyProperties(resourceUpdateDTO, resource);
		resourceRepository.save(resource);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String resourceId) {
		resourceRepository.deleteById(resourceId);
	}

}
