package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record UserRolesUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") String userRolesId,

		@NotBlank(message = "It cannot be blank") String userId,

		@NotBlank(message = "It cannot be blank") String roleId

) {

}
