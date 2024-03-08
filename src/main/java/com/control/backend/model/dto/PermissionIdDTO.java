package com.control.backend.model.dto;

import com.control.backend.model.dto.validation.ExistsPermissionId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record PermissionIdDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsPermissionId String permissionId

) {

}
