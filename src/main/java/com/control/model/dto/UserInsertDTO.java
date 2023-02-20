package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsUserEmailInsert;
import com.control.model.validation.ExistsUserName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	@ExistsUserName
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	@ExistsUserEmailInsert
	private String userEmail;

	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;
		
}
