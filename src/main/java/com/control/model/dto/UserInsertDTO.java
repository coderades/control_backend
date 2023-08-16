package com.control.model.dto;

import java.io.Serializable;

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
	private Boolean userIsCredentialsNonExpired;

	@Size(min = 4, max = 50, message = "Enter between 4 and 50 characters")
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String userEmail;
	
	@Size(min = 3, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;
		
}
