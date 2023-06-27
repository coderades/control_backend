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
import com.control.model.dto.RoleIdDTO;
import com.control.model.dto.RolePostDTO;
import com.control.model.dto.RolePutDTO;
import com.control.repository.RoleRepository;

@Service
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Page<Role> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	public Optional<Role> findById(String roleId) {
		return roleRepository.findById(roleId);
	}

	public Role findByName(String roleName) {
		return roleRepository.findByRoleName(roleName);
	}

	public List<Role> findByNameContaining(String roleName) {
		return roleRepository.findByRoleNameIgnoreCaseContaining(roleName);
	}

	public List<Object[]> findByHasAnyRole(String applicationId) {
		return roleRepository.findByHasAnyRole(applicationId);
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(RolePostDTO roleInsertDTO) {
		final var entity = new Role();
		BeanUtils.copyProperties(roleInsertDTO, entity);
		roleRepository.save(entity);
		return entity.getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(RolePutDTO roleUpdateDTO) {
		final var entity = new Role();
		BeanUtils.copyProperties(roleUpdateDTO, entity);
		roleRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(RoleIdDTO roleIdDTO) {
		roleRepository.deleteById(roleIdDTO.getRoleId());
	}

}
