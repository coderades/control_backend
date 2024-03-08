package com.control.backend.model.dto;

import com.control.backend.model.dto.validation.ExistsRoleId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record RoleIdDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsRoleId String roleId

) {

}
