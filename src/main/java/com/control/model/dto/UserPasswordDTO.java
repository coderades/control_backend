package com.control.model.dto;

import jakarta.validation.constraints.Size;

public class UserPasswordDTO extends UserIdDTO {

	private static final long serialVersionUID = 2536872250822949319L;

	@Size(min = 33, max = 37, message = "Enter between 1 and 70 characters")
	private String userPassword;

}
