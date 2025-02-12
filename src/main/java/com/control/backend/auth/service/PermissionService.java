package com.control.backend.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.Permission;
import com.control.backend.auth.model.dto.PermissionDTO;
import com.control.backend.auth.model.dto.view.PermissionListViewDTO;
import com.control.backend.auth.repository.PermissionRepository;

@Service
public class PermissionService {

	@Autowired
	private PermissionRepository permissionRepository;

	public Optional<Permission> findById(Long permissionId) {
		return permissionRepository.findById(permissionId);
	}
	
	public List<PermissionListViewDTO> permissionListView() {
		return permissionRepository.permissionListView();
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(PermissionDTO permissionDTO) {
		final var permission = new Permission();
		BeanUtils.copyProperties(permissionDTO, permission);
		return permissionRepository.save(permission).getPermissionId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long permissionId, PermissionDTO permissionDTO) {
		final var permission = new Permission();
		BeanUtils.copyProperties(permissionDTO, permission);
		permission.setPermissionId(permissionId);
		permissionRepository.save(permission);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long permissionId) {
		permissionRepository.deleteById(permissionId);
	}

}
