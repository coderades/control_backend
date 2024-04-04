package com.control.backend.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.control.backend.model.Role;
import com.control.backend.model.dto.RoleNameDTO;

//https://www.baeldung.com/spring-data-derived-queries
public interface RoleRepository extends JpaRepository<Role, String> {

	Boolean existsByRoleIdIsNotAndRoleName(String roleId, String roleName);

	Boolean existsByRoleName(String roleName);

	Role findByRoleName(String roleName);

	List<Role> findByRoleIsEnabled(Boolean roleIsEnabled);

	List<Role> findByRoleNameIgnoreCaseContaining(String roleName);

	@Query("SELECT NEW com.control.backend.model.dto.RoleNameDTO(role.roleName) "
			+ "FROM Application application INNER JOIN "
			+ "Resource resource ON application.applicationId = resource.applicationId INNER JOIN "
			+ "Permission permission ON resource.resourceId = permission.resourceId INNER JOIN "
			+ "RolePermissions rolePermissions ON permission.permissionId = rolePermissions.permissionId INNER JOIN "
			+ "Role role ON rolePermissions.roleId = role.roleId "
			+ "WHERE (application.applicationIsEnabled = true) AND (resource.resourceIsEnabled = true) AND (permission.permissionIsPublic = false) AND permission.permissionMethod = ?2 AND (application.applicationPublicKey = ?1) AND (role.roleIsEnabled = true) "
			+ "GROUP BY role.roleName")
	List<RoleNameDTO> findByPremissionResourceRole(String applicationAccessToken, String httpMethod);

}