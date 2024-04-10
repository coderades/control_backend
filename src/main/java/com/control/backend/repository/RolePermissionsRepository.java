package com.control.backend.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.model.RolePermissions;

public interface RolePermissionsRepository extends JpaRepository<RolePermissions, String> {

}