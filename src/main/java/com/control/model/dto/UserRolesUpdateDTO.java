package com.control.model.dto;

import com.control.model.dto.validation.ExistsUserRolesId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record UserRolesUpdateDTO(
		
		@Id @NotBlank(message = "It cannot be blank") @ExistsUserRolesId String userRolesId,
		
		@NotBlank(message = "It cannot be blank") String userId,
		
		@NotBlank(message = "It cannot be blank") String roleId,
		
		@NotBlank(message = "It cannot be blank") String userRolesCreatedBy
		
) {

}
