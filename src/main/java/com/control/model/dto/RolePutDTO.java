package com.control.model.dto;

import org.hibernate.annotations.DynamicUpdate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@DynamicUpdate
public class RolePutDTO extends RoleIdDTO {

	private static final long serialVersionUID = -1506397500308371712L;

	@NotBlank(message = "It cannot be blank")
	private Boolean roleIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String roleName;

}
