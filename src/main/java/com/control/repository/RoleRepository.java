package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.model.Role;

//https://www.baeldung.com/spring-data-derived-queries
public interface RoleRepository extends JpaRepository<Role, String> {

	Boolean existsByRoleIdIsNotAndRoleName(String roleId, String roleName);

	Boolean existsByRoleName(String roleName);

	Role findByRoleName(String roleName);

	List<Role> findByRoleNameIgnoreCaseContaining(String roleName);

}
