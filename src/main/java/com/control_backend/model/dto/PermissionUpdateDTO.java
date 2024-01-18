package com.control_backend.model.dto;

import com.control_backend.model.dto.validation.ExistsPermissionId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record PermissionUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsPermissionId String permissionId,
		
		@NotNull(message = "It cannot be null") String resourceId,

		@NotNull(message = "It cannot be null") Boolean permissionIsPublic,

		@NotBlank(message = "It cannot be blank") String permissionMethod

) {

}
