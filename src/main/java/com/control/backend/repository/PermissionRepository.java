package com.control.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.control.backend.model.Permission;

public interface PermissionRepository extends JpaRepository<Permission, String> {

	List<Permission> findByPermissionIsPublic(Boolean permissionIsPublic);

	List<Permission> findByPermissionMethod(String permissionMethod);

}