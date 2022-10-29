package com.control.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.model.UserRoles;

public interface UserRolesRepository  extends JpaRepository<UserRoles, String> {

}
