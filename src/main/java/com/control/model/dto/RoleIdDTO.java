package com.control.model.dto;

import com.control.model.dto.validation.ExistsRoleId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record RoleIdDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsRoleId String roleId

) {

}
