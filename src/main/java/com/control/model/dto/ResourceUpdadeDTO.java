package com.control.model.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResourceUpdadeDTO implements Serializable {

	private static final long serialVersionUID = -8130065148349958506L;

	@Id
	@NotBlank(message = "It cannot be blank")
	private String resourceId;

	@NotBlank(message = "It cannot be blank")
	private String applicationId;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean resourceIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String resourceName;

	@Size(min = 2, max = 200, message = "Enter between 2 and 500 characters")
	private String resourceUrl;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean resourceModIsCreate;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean resourceModIsRead;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean resourceModIsUpdate;

	@NotNull(message = "It cannot be null")
	@NotEmpty(message = "It cannot be empty")
	private Boolean resourceModIsDelete;

}
