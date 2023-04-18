package com.control.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateDTO extends UserIdDTO {

	private static final long serialVersionUID = 3445600659154104881L;

	@NotNull(message = "It cannot be null")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	private Boolean userIsCredentialsNonDiscredited;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String userEmail;

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;

}
