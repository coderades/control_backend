package com.control.backend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

	List<UserRoles> findByUserId(Long userId);

	List<UserRoles> findByRoleId(Long roleId);

}
