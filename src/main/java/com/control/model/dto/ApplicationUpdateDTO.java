package com.control.model.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ApplicationUpdateDTO implements Serializable {

	private static final long serialVersionUID = -3241393212626215402L;

	@Id
	@NotBlank(message = "It cannot be blank")
	private String applicationId;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean applicationIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String applicationName;

	@Email(message = "Incorrect format")
	@Size(min = 8, max = 50, message = "Enter between 8 and 50 characters")
	private String applicationEmail;

	@NotBlank(message = "It cannot be blank")
	private String applicationSecret;

}
