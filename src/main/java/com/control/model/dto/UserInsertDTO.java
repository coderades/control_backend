package com.control.model.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.control.model.validation.ExistsUserEmailInsert;
import com.control.model.validation.ExistsUserNameInsert;

import lombok.Data;

@Data
public class UserInsertDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@NotNull(message = "It cannot be null")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	private Boolean userIsCredentialsNonDiscredited;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	@ExistsUserNameInsert
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	@ExistsUserEmailInsert
	private String userEmail;
	
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;
		
}
