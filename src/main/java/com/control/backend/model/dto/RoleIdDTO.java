package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record RoleIdDTO(

		@Id @NotBlank(message = "It cannot be blank") String roleId

) {

}
