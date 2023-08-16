package com.control.model.dto;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(@NotBlank(message = "It cannot be blank") String userName,
		
		@NotBlank(message = "It cannot be blank") String userPassword) {
	
}
