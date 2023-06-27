package com.control.model.dto;

import java.io.Serializable;

import com.control.model.dto.validation.NotExistsUserEmail;
import com.control.model.dto.validation.NotExistsUserName;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPostDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@NotNull(message = "It cannot be null")
	private Boolean userIsEnabled;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonExpired;

	@NotNull(message = "It cannot be null")
	private Boolean userIsAccountNonLocked;

	@NotNull(message = "It cannot be null")
	private Boolean userIsCredentialsNonExpired;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	@NotExistsUserName
	private String userName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	@NotExistsUserEmail
	private String userEmail;
	
	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	

	private String userPasswordToken;

	private String userRememberToken;

	private Long userPinCode;
		
}
