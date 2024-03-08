package com.control.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, String> {

	List<UserRoles> findByRoleId(String roleId);

	List<UserRoles> findByUserId(String userId);

}
