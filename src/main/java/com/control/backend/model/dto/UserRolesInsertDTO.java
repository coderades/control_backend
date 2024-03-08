package com.control.backend.model.dto;

import jakarta.validation.constraints.NotBlank;

public record UserRolesInsertDTO(
		
		@NotBlank(message = "It cannot be blank") String userId,
		
		@NotBlank(message = "It cannot be blank") String roleId
		
) {

}
