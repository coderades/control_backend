package com.control.backend.auth.model.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.Size;

public record UserPasswordDTO(

		@Size(min = 2, max = 70, message = "Enter between 2 and 70 characters") String userPassword

) {

	public UserPasswordDTO {
		userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}

}
