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

import com.control.backend.model.Role;
import com.control.backend.model.dto.RoleIdDTO;
import com.control.backend.model.dto.RoleInsertDTO;
import com.control.backend.model.dto.RoleNameDTO;
import com.control.backend.model.dto.RoleUpdateDTO;
import com.control.backend.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Page<Role> findAll(Pageable pageable) {
		final var role = roleRepository.findAll(pageable);
		return role;
	}

	public Optional<Role> findById(String roleId) {
		final var role = roleRepository.findById(roleId);
		return role;
	}

	public Role findByName(String roleName) {
		final var role = roleRepository.findByRoleName(roleName);
		return role;
	}

	public List<Role> findByIsEnabled(Boolean roleIsEnabled) {
		final var role = roleRepository.findByRoleIsEnabled(roleIsEnabled);
		return role;
	}

	public List<Role> findByNameContaining(String roleName) {
		final var role = roleRepository.findByRoleNameIgnoreCaseContaining(roleName);
		return role;
	}

	public List<RoleNameDTO> findByPremissionResourceRole(String applicationPublicKey, HttpMethod httpMethod) {
		final var permission = roleRepository.findByPremissionResourceRole(applicationPublicKey, httpMethod.name());
		return permission;
	}

	@Transactional(rollbackFor = Exception.class)
	public RoleIdDTO save(RoleInsertDTO roleInsertDTO) {
		final Role role = new Role();
		BeanUtils.copyProperties(roleInsertDTO, role);
		return new RoleIdDTO(roleRepository.save(role).getRoleId());
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(RoleUpdateDTO roleUpdateDTO) {
		final Role role = new Role();
		BeanUtils.copyProperties(roleUpdateDTO, role);
		roleRepository.save(role);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String roleId) {
		roleRepository.deleteById(roleId);
	}

}
