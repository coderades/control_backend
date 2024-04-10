package com.control.backend.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.model.Permission;
import com.control.backend.model.dto.PermissionIdDTO;
import com.control.backend.model.dto.PermissionInsertDTO;
import com.control.backend.model.dto.PermissionUpdateDTO;
import com.control.backend.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public Page<Permission> findAll(Pageable pageable) {
		final var permission = permissionRepository.findAll(pageable);
		return permission;
	}

	public Optional<Permission> findById(String permissionId) {
		final var permission = permissionRepository.findById(permissionId);
		return permission;
	}

	public List<Permission> findByIsPublic(Boolean permissionIsPublic) {
		final var permission = permissionRepository.findByPermissionIsPublic(permissionIsPublic);
		return permission;
	}

	public List<Permission> findByMethod(String permissionMethod) {
		final var permission = permissionRepository.findByPermissionMethod(permissionMethod);
		return permission;
	}

	@Transactional(rollbackFor = Exception.class)
	public PermissionIdDTO save(PermissionInsertDTO permissionInsertDTO) {
		final Permission permission = new Permission();
		BeanUtils.copyProperties(permissionInsertDTO, permission);
		return new PermissionIdDTO(permissionRepository.save(permission).getPermissionId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(PermissionUpdateDTO permissionUpdateDTO) {
		final Permission permission = new Permission();
		BeanUtils.copyProperties(permissionUpdateDTO, permission);
		permissionRepository.save(permission);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String permissionId) {
		permissionRepository.deleteById(permissionId);
	}

}
