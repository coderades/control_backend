package com.control.model.dto;

import java.io.Serializable;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicationPostDTO implements Serializable {

	private static final long serialVersionUID = -527506195465983324L;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean applicationIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String applicationName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String applicationEmail;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private String applicationSecret;

}
