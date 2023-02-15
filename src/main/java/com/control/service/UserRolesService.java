package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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
public class UserRolesService {

	@Autowired
	private RoleRepository roleRepository;

	public Page<Role> findAll(Pageable pageable) {
		final var entity = roleRepository.findAll(pageable);

		log.info("Return: Elements={}, Object={}", entity.getSize(), entity);

		return entity;
	}

	public Optional<Role> findById(String roleId) {
		final var entity = roleRepository.findById(roleId);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public Role findByName(String roleName) {
		final var entity = roleRepository.findByRoleName(roleName);

		log.info("Return: Object={}", entity);

		return entity;
	}

	public List<?> findByNameContaining(String roleName) {
		final var entity = roleRepository.findByRoleNameIgnoreCaseContaining(roleName);

		log.info("Return: Elements={}, Object={}", entity.size(), entity);

		return entity.size() == 0 ? null : entity;
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(RoleInsertDTO roleInsertDTO) {
		final var entity = new Role();

		roleRepository.save(entity);
		log.info("Return: roleId={}", entity.getRoleId());

		return entity.getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(RoleUpdateDTO roleUpdateDTO) {
		final var entity = new Role();

		roleRepository.save(entity);
		log.info("Status: OK");
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String roleId) {
		roleRepository.deleteById(roleId);
		log.info("Status: OK");
	}

}
