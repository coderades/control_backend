package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PermissionUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") String permissionId,

		@NotBlank(message = "It cannot be blank") String resourceId,

		@NotNull(message = "It cannot be null") Boolean permissionIsPublic,

		@NotBlank(message = "It cannot be blank") String permissionMethod

) {

}
