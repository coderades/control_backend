package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;

public record UserRolesDTO(

		@NotNull(message = "It cannot be null") Long userId,

		@NotNull(message = "It cannot be null") Long roleId

) {
}
