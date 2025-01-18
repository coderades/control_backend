package com.control.backend.auth.model.dto;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserDTO(

		@NotNull(message = "It cannot be null") Boolean userIsEnabled,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonExpired,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonLocked,

		@NotNull(message = "It cannot be null") Boolean userIsCredentialsNonExpired,

		@Size(min = 2, max = 30, message = "Enter between 2 and 30 characters") String userName,

		@Size(min = 2, max = 70, message = "Enter between 2 and 70 characters") String userPassword,

		@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters") @Email(message = "Incorrect format") String userEmail

) {

	public UserDTO {
		userPassword = new BCryptPasswordEncoder().encode(userPassword);
	}

}
