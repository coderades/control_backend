package com.control.backend.model.dto;

import com.control.backend.model.dto.validation.ExistsUserRolesId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record UserRolesUpdateDTO(
		
		@Id @NotBlank(message = "It cannot be blank") @ExistsUserRolesId String userRolesId,
		
		@NotBlank(message = "It cannot be blank") String userId,
		
		@NotBlank(message = "It cannot be blank") String roleId
		
) {

}
