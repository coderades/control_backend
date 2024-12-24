package com.control.backend.auth.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.auth.model.UserRoles;

public interface UserRolesRepository extends JpaRepository<UserRoles, Long> {

}
