package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;

public record RoleResourcesDTO(
		
		@NotNull(message = "It cannot be null") Long roleId,
		
		@NotNull(message = "It cannot be null") Long resource_id,

		@NotNull(message = "It cannot be null") Boolean roleResourcesIsCreate,

		@NotNull(message = "It cannot be null") Boolean roleResourceIsRead,

		@NotNull(message = "It cannot be null") Boolean roleResourceIsUpdate,

		@NotNull(message = "It cannot be null") Boolean roleResourceIsDelete

) {

}
