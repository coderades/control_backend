package com.control.backend.model.dto;

import jakarta.validation.constraints.Size;

public record RoleNameDTO(

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String roleName

) {

}
