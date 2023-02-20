package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsApplicationId;
import com.control.model.validation.ExistsApplicationTenantId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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
	@ExistsApplicationId
	private String applicationId;

	@NotNull(message = "It cannot be null")
	private Boolean applicationTenantIsEnabled;

	@Size(min = 2, max = 30, message = "Enter between 2 and 30 characters")
	private String applicationTenantName;

	@Size(min = 8, max = 500, message = "Enter between 8 and 500 characters")
	private String applicationTenantUrl;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String applicationTenantUserName;

	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters")
	private String applicationTenantPassword;

	@Size(min = 10, max = 50, message = "Enter between 1 and 70 characters")
	private String applicationTenantDriverClassName;

	@NotNull(message = "It cannot be null")
	private Boolean applicationTenantInitialize;

}
