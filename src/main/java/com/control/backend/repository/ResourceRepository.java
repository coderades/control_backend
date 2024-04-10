package com.control.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.backend.model.Resource;
import com.control.backend.model.dto.ResourcePathDTO;

public interface ResourceRepository extends JpaRepository<Resource, String> {

	Resource findByResourceName(String resourceName);

	List<Resource> findByResourceNameIgnoreCaseContainingOrResourcePathIgnoreCaseContaining(String resourceName,
			String resourcePath);

	@Query("SELECT NEW com.control.backend.model.dto.ResourcePathDTO(resource.resourcePath) "
			+ "FROM Application application INNER JOIN "
			+ "Resource resource ON application.applicationId = resource.applicationId INNER JOIN "
			+ "Permission permission ON resource.resourceId = permission.resourceId "
			+ "WHERE (application.applicationIsEnabled = true) AND (resource.resourceIsEnabled = true) AND (permission.permissionIsPublic = ?3) AND permission.permissionMethod = ?2 AND (application.applicationPublicKey = ?1)"
			+ "GROUP BY resource.resourcePath")
	List<ResourcePathDTO> findByResourcePath(String applicationPublicKey, String httpMethod,
			Boolean permissionIsPublic);

}