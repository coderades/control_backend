package com.control.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RolePostDTO implements Serializable {

	private static final long serialVersionUID = -363668585366767814L;

	@NotBlank(message = "It cannot be blank")
	private Boolean roleIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String roleName;

}
