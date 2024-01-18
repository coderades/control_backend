package com.control_backend.model.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.control_backend.model.dto.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordDTO(
		
		@Id @NotBlank(message = "It cannot be blank") @ExistsUserId String userId,
		@Size(min = 4, max = 70, message = "Enter between 4 and 70 characters") String userPassword

) {

	public UserUpdatePasswordDTO{
		userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}
}
