package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Role;
import com.control.model.dto.RoleInsertDTO;
import com.control.model.dto.RoleUpdateDTO;
import com.control.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Page<Role> findAll(Pageable pageable) {
		final var role = roleRepository.findAll(pageable);
		return role;
	}

	public Optional<Role> findById(String RoleId) {
		final var role = roleRepository.findById(RoleId);
		return role;
	}

	public Role findByName(String RoleName) {
		final var role = roleRepository.findByRoleName(RoleName);
		return role;
	}

	public List<Role> findByNameContaining(String roleName) {
		final var role = roleRepository.findByRoleNameIgnoreCaseContaining(roleName);
		return role;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(RoleInsertDTO RoleInsertDTO) {
		final var role = new Role();

		BeanUtils.copyProperties(RoleInsertDTO, role);
		roleRepository.save(role);

		return role.getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(RoleUpdateDTO RoleUpdateDTO) {
		final var role = new Role();

		BeanUtils.copyProperties(RoleUpdateDTO, role);
		roleRepository.save(role);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String roleId) {
		roleRepository.deleteById(roleId);
	}

}
