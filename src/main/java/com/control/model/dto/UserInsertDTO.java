package com.control.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record UserInsertDTO(@NotNull(message = "It cannot be null") Boolean userIsEnabled,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonExpired,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonLocked,

		@NotNull(message = "It cannot be null") Boolean userIsCredentialsNonExpired,

		@Size(min = 4, max = 50, message = "Enter between 4 and 50 characters") String userName,

		@Email(message = "Incorrect format") @Size(min = 8, max = 50, message = "Enter between 8 and 50 characters") String userEmail,

		@Size(min = 4, max = 70, message = "Enter between 4 and 70 characters") String userPassword,

		String userPasswordToken,

		String userRememberToken,

		Long userPinCode
) {

}
