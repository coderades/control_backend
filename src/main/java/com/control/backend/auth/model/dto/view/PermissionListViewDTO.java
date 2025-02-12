package com.control.backend.auth.model.dto.view;

public record PermissionListViewDTO(

		String roleName,

		String methodName,
		
		Boolean permissionIsWildcard,

		String resourcePath

) {

}
