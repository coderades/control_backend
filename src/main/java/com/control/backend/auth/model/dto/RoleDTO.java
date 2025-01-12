package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoleDTO(

		@NotNull(message = "It cannot be null") Boolean roleIsEnabled,

		@Size(min = 2, max = 50, message = "Enter between 2 and 30 characters") String roleName,

		@Size(max = 400, message = "Enter between 2 and 30 characters") String roleDescription

) {
}
