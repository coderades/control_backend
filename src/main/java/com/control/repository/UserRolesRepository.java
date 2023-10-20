package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, String> {

	List<UserRoles> findByRoleId(String roleId);

	List<UserRoles> findByUserId(String userId);

}
