package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsUserEmailUpdate;
import com.control.model.validation.ExistsUserId;
import com.control.model.validation.ExistsUserNameUpdate;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@ExistsUserNameUpdate
@ExistsUserEmailUpdate
public class UserUpdateDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsUserId
	private String userId;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
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
