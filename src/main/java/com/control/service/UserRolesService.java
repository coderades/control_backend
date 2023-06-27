package com.control.service;

import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.UserRoles;
import com.control.model.dto.UserRolesIdDTO;
import com.control.model.dto.UserRolesPostDTO;
import com.control.model.dto.UserRolesPutDTO;
import com.control.repository.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository roleRepository;

	public Page<UserRoles> findAll(Pageable pageable) {
		return roleRepository.findAll(pageable);
	}

	public Optional<UserRoles> findById(String roleId) {
		return roleRepository.findById(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	public String save(UserRolesPostDTO userRolesPostDTO) {
		final var entity = new UserRoles();
		BeanUtils.copyProperties(userRolesPostDTO, entity);
		roleRepository.save(entity);
		return entity.getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(UserRolesPutDTO userRolesPutDTO) {
		final var entity = new UserRoles();
		BeanUtils.copyProperties(userRolesPutDTO, entity);
		roleRepository.save(entity);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(UserRolesIdDTO userRolesIdDTO) {
		roleRepository.deleteById(userRolesIdDTO.getUserRolesId());
	}

}
