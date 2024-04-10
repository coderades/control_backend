package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record RolePermissionsInsertDTO(

		@Id @NotBlank(message = "It cannot be blank") String rolePermissionsId,

		@NotBlank(message = "It cannot be blank") String roleId,

		@NotBlank(message = "It cannot be blank") String permissionId

) {

}
