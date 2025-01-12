package com.control.backend.auth.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.backend.auth.model.UserRoles;
import com.control.backend.auth.model.dto.UserRolesDTO;
import com.control.backend.auth.repository.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;

	public Optional<UserRoles> findById(Long userRolesId) {
		return userRolesRepository.findById(userRolesId);
	}

	public List<UserRoles> findByUserId(Long userId) {
		return userRolesRepository.findByUserId(userId);
	}

	public List<UserRoles> findByRoleId(Long roleId) {
		return userRolesRepository.findByRoleId(roleId);
	}

	@Transactional(rollbackFor = Exception.class)
	public Long save(UserRolesDTO userRolesDTO) {
		final var userRoles = new UserRoles();
		BeanUtils.copyProperties(userRolesDTO, userRoles);
		return userRolesRepository.save(userRoles).getRoleId();
	}

	@Transactional(rollbackFor = Exception.class)
	public void save(Long userRolesId, UserRolesDTO userRolesDTO) {
		final var userRoles = new UserRoles();
		BeanUtils.copyProperties(userRolesDTO, userRoles);
		userRoles.setUserId(userRolesId);
		userRolesRepository.save(userRoles);
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(Long userRolesId) {
		userRolesRepository.deleteById(userRolesId);
	}

}
