package com.control.backend.auth.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.backend.auth.model.Permission;
import com.control.backend.auth.model.dto.view.PermissionListViewDTO;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

	//@Query("SELECT role.roleName, resource.resourcePath, permission.permissionIsWildcard, permission.permissionIsCreate, permission.permissionIsRead, permission.permissionIsUpdate, permission.permissionIsDelete "
//	@Query("SELECT NEW com.control.backend.auth.model.dto.PermissionListDTO(role.roleName, resource.resourcePath, permission.permissionIsWildcard, permission.permissionIsCreate, permission.permissionIsRead, permission.permissionIsUpdate, permission.permissionIsDelete) "
//			+ "FROM Resource resource INNER JOIN Permission permission ON resource.resourceId = permission.resourceId LEFT OUTER JOIN Role role ON permission.roleId = role.roleId "
//			+ "WHERE (resource.resourceIsEnabled = true) AND (role.roleIsEnabled = true OR role.roleIsEnabled IS NULL)")
	// + "WHERE (resource.resourceIsEnabled = ?1) AND (role.roleIsEnabled = true OR
	// role.roleIsEnabled IS NULL)")
	
	
	@Query("SELECT NEW com.control.backend.auth.model.dto.view.PermissionListViewDTO(role.roleName, method.methodName, permission.permissionIsWildcard, resource.resourcePath) "
			+ "FROM Resource resource INNER JOIN "
			+ "Permission permission ON resource.resourceId = permission.resourceId INNER JOIN "
			+ "Method method ON permission.methodId = method.methodId LEFT OUTER JOIN "
			+ "Role role ON permission.roleId = role.roleId "
			+ "WHERE (resource.resourceIsEnabled = true) AND (method.methodIsEnabled = true) AND (role.roleIsEnabled = true OR role.roleIsEnabled IS NULL)")
	List<PermissionListViewDTO> permissionListView();

}
