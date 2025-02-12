package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;

public record PermissionDTO(
		
		Long roleId,
		
		@NotNull(message = "It cannot be null") Long resourceId,
		
		@NotNull(message = "It cannot be null") Boolean permissionIsWildcard,

		@NotNull(message = "It cannot be null") Boolean permissionIsCreate,

		@NotNull(message = "It cannot be null") Boolean permissionIsRead,

		@NotNull(message = "It cannot be null") Boolean permissionIsUpdate,

		@NotNull(message = "It cannot be null") Boolean permissionIsDelete

) {

}
