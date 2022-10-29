package com.control.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.Role;
import com.control.model.dto.RoleInsertDTO;
import com.control.model.dto.RoleUpdateDTO;
import com.control.repository.RoleRepository;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class RoleService {

	private final RoleRepository roleRepository;

	public RoleService(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	public Page<?> findAll(Pageable pageable) {
		final var role = roleRepository.findAll(pageable);

		log.info("Elements: {}, Object: {}", role.getSize(), role.getContent());

		return role;
	}

	public Role findById(String roleId) {
		final var role = roleRepository.findById(roleId);

		log.info("Object: {}", role);

		return role.isPresent() ? role.get() : null;
	}

	public Role findByName(String roleName) {
		final var role = roleRepository.findByRoleName(roleName);

		log.info("Object: {}", role);

		return role == null ? null : role;
	}

	public List<?> findByNameContaining(String roleName) {
		final var role = roleRepository.findByRoleNameIgnoreCaseContaining(roleName);

		log.info("Elements: {}, Object: {}", role.size(), role);

		return role.size() == 0 ? null : role;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(RoleInsertDTO roleInsertDTO) {
		final var role = new Role();

		roleRepository.save(role);
		log.info("Return: roleId={}", role.getRoleId());

		return role.getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(RoleUpdateDTO roleUpdateDTO) {
		final var role = new Role();

		roleRepository.save(role);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String roleId) {
		roleRepository.deleteById(roleId);
		log.info("Status: OK");
	}

}
