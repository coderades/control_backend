package com.control.backend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	Role findByRoleName(String roleName);

	List<Role> findByRoleNameIgnoreCaseContaining(String roleName);
	
}