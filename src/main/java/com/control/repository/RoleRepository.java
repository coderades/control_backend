package com.control.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.control.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {

	Role findByRoleName(String roleName);
	
	List<Role> findByRoleNameIgnoreCaseContaining(String roleName);
	
	@Query("SELECT role.roleName, resource.resourceUrl, rolePermissions.rolePermissionsModIsCreate, rolePermissions.rolePermissionsModIsRead, rolePermissions.rolePermissionsModIsUpdate, rolePermissions.rolePermissionsModIsDelete FROM Role role INNER JOIN RolePermissions rolePermissions ON role.roleId = rolePermissions.roleId INNER JOIN Resource resource ON rolePermissions.resourceId = resource.resourceId INNER JOIN Application application ON resource.applicationId = application.applicationId WHERE (role.roleIsEnabled = 1) AND (application.applicationId = ?1) AND (application.applicationIsEnabled = 1) AND (resource.resourceIsEnabled = 1) ORDER BY role.roleName, resource.resourceUrl")	
	List<Object[]> findByHasAnyRole(String applicationId);

}
