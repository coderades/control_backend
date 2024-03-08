package com.control.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PermissionInsertDTO(

		@NotNull(message = "It cannot be null") String resourceId,

		@NotNull(message = "It cannot be null") Boolean permissionIsPublic,

		@NotBlank(message = "It cannot be blank") String permissionMethod

) {

}
