package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;

public record PermissionDTO(

		@NotNull(message = "It cannot be null") Boolean permissionIsWildcard,
		
		@NotNull(message = "It cannot be null") Long  roleId,
		
		@NotNull(message = "It cannot be null") Long  method_id,
		
		@NotNull(message = "It cannot be null") Long resource_id
		
) {

}
