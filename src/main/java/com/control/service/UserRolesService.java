package com.control.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.control.model.UserRoles;
import com.control.repository.UserRolesRepository;

@Service
public class UserRolesService {

	@Autowired
	private UserRolesRepository userRolesRepository;

	public Page<UserRoles> findAll(Pageable pageable) {
		final var userRoles = userRolesRepository.findAll(pageable);
		return userRoles;
	}

	public Optional<UserRoles> findById(String userRolesId) {
		final var userRoles = userRolesRepository.findById(userRolesId);
		return userRoles;
	}

	public List<UserRoles> findByRoleId(String roleId) {
		final var userRoles = userRolesRepository.findByRoleId(roleId);
		return userRoles;
	}

	public List<UserRoles> findByUserId(String userId) {
		final var userRoles = userRolesRepository.findByUserId(userId);
		return userRoles;
	}

	@Transactional(rollbackFor = Exception.class)
	public void delete(String userRolesId) {
		userRolesRepository.deleteById(userRolesId);
	}

}
