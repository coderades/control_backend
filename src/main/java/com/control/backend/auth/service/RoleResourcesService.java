package com.control.backend.auth.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.RoleResources;
import com.control.backend.auth.model.dto.RoleDTO;
import com.control.backend.auth.model.dto.RoleResourcesDTO;
import com.control.backend.auth.repository.RoleResourcesRepository;

@Service
public class RoleResourcesService {

	@Autowired
	private RoleResourcesRepository roleResourcesRepository;

	public Page<RoleResources> findAll(Pageable pageable) {
		return roleResourcesRepository.findAll(pageable);
	}

	public Optional<RoleResources> findById(Long roleResourcesId) {
		return roleResourcesRepository.findById(roleResourcesId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(RoleResourcesDTO roleResourcesDTO) {
		final var roleResources = new RoleResources();
		BeanUtils.copyProperties(roleResourcesDTO, roleResources);
		return roleResourcesRepository.save(roleResources).getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long roleResourcesId, RoleDTO roleResourcesDTO) {
		final var roleResources = new RoleResources();
		BeanUtils.copyProperties(roleResourcesDTO, roleResources);
		roleResources.setRoleResourcesId(roleResourcesId);
		roleResourcesRepository.save(roleResources);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long roleResourcesId) {
		roleResourcesRepository.deleteById(roleResourcesId);
	}

}
