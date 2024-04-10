package com.control.backend.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.model.RolePermissions;
import com.control.backend.model.dto.RolePermissionsIdDTO;
import com.control.backend.model.dto.RolePermissionsInsertDTO;
import com.control.backend.repository.RolePermissionsRepository;

@Service
public class RolePermissionsService {

	@Autowired
	private RolePermissionsRepository rolePermissionsRepository;

	public Page<RolePermissions> findAll(Pageable pageable) {
		final var rolePermissions = rolePermissionsRepository.findAll(pageable);
		return rolePermissions;
	}

	public Optional<RolePermissions> findById(String rolePermissionsId) {
		final var rolePermissions = rolePermissionsRepository.findById(rolePermissionsId);
		return rolePermissions;
	}

	@Transactional(rollbackFor = Exception.class)
	public RolePermissionsIdDTO save(RolePermissionsInsertDTO rolePermissionsInsertDTO) {
		final RolePermissions rolePermissions = new RolePermissions();
		BeanUtils.copyProperties(rolePermissionsInsertDTO, rolePermissions);
		return new RolePermissionsIdDTO(rolePermissionsRepository.save(rolePermissions).getRoleId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String rolePermissionsId) {
		rolePermissionsRepository.deleteById(rolePermissionsId);
	}

}
