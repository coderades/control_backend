package com.control.backend.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

import com.control.backend.model.dto.ResourcePathDTO;
import com.control.backend.repository.ResourceRepository;

@Service
public class ResourceService {

	@Autowired
	private ResourceRepository resourceRepository;

	public List<ResourcePathDTO> findByResourcePath(String applicationKeyAccess, HttpMethod httpMethod,
			Boolean permissionIsPublic) {
		final var resource = resourceRepository.findByResourcePath(applicationKeyAccess, httpMethod.name(),
				permissionIsPublic);
		return resource;
	}

}
