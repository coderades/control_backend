package com.control.backend.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.Role;
import com.control.backend.auth.model.dto.RoleDTO;
import com.control.backend.auth.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	public Optional<Role> findById(Long roleId) {
		return roleRepository.findById(roleId);
	}

	public Role findByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	public List<Role> findByNameContaining(String roleName) {
		return roleRepository.findByRoleNameIgnoreCaseContaining(roleName);
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(RoleDTO roleDTO) {
		final var role = new Role();
		BeanUtils.copyProperties(roleDTO, role);
		return roleRepository.save(role).getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long roleId, RoleDTO roleDTO) {
		final var role = new Role();
		BeanUtils.copyProperties(roleDTO, role);
		role.setRoleId(roleId);
		roleRepository.save(role);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long roleId) {
		roleRepository.deleteById(roleId);
	}

}
