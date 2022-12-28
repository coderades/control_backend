package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.control.model.validation.ExistsApplicationTenantId;

import lombok.Data;

@Data
public class ApplicationTenantUpdateDTO implements Serializable {

	private static final long serialVersionUID = -4901786420132238929L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsApplicationTenantId
	private String applicationTenantId;

	@NotNull(message = "It cannot be null")
	private String applicationId;

	@NotNull(message = "It cannot be null")
	private Boolean applicationTenantIsEnabled;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 2, max = 30, message = "Enter between 2 and 30 characters")
	private String applicationTenantName;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 8, max = 500, message = "Enter between 8 and 500 characters")
	private String applicationTenantUrl;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String applicationTenantUserName;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters")
	private String applicationTenantPassword;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 10, max = 50, message = "Enter between 1 and 70 characters")
	private String applicationTenantDriverClassName;

	@NotNull(message = "It cannot be null")
	private Boolean applicationTenantInitialize;

}
