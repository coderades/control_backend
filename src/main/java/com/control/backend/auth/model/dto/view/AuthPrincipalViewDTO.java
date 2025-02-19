package com.control.backend.auth.model.dto.view;

public record AuthPrincipalViewDTO(

		String roleName,

		String methodName,

		Boolean permissionIsWildcard,

		String resourcePath

) {

}
