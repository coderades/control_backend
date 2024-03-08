package com.control.backend.model.dto;

import com.control.backend.model.dto.validation.ExistsRoleId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record RoleUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsRoleId String roleId,

		@NotNull(message = "It cannot be null") Boolean roleIsEnabled,

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String roleName,
		
		@NotNull(message = "It cannot be null") String roleDescription

) {

}
