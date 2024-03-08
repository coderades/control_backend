package com.control.backend.model.dto;

import com.control.backend.model.dto.validation.ExistsUserEmailForAnotherUserId;
import com.control.backend.model.dto.validation.ExistsUserId;
import com.control.backend.model.dto.validation.ExistsUserNameForAnotherUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

@ExistsUserNameForAnotherUserId(field = "userName", fieldMatch = "userId")
@ExistsUserEmailForAnotherUserId(field = "userEmail", fieldMatch = "userId")
public record UserUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsUserId String userId,

		@NotNull(message = "It cannot be null") Boolean userIsEnabled,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonExpired,

		@NotNull(message = "It cannot be null") Boolean userIsAccountNonLocked,

		@NotNull(message = "It cannot be null") Boolean userIsCredentialsNonExpired,

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String userName,

		@Email(message = "Incorrect format") @Size(min = 8, max = 50, message = "Enter between 8 and 50 characters") String userEmail,

		String userPasswordToken,

		String userRememberToken,

		Long userPinCode

) {

}
