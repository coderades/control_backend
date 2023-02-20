package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsApplicationId;
import com.control.model.validation.ExistsResourceId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ResourceUpdadeDTO implements Serializable {

	private static final long serialVersionUID = -8130065148349958506L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsResourceId
	private String resourceId;

	@NotNull(message = "It cannot be null")
	@ExistsApplicationId
	private String applicationId;

	@NotNull(message = "It cannot be null")
	private Boolean resourceIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String resourceName;

	@Size(min = 2, max = 200, message = "Enter between 2 and 500 characters")
	private String resourceUrl;

	@NotNull(message = "It cannot be null")
	private Boolean resourceModIsCreate;

	@NotNull(message = "It cannot be null")
	private Boolean resourceModIsRead;

	@NotNull(message = "It cannot be null")
	private Boolean resourceModIsUpdate;

	@NotNull(message = "It cannot be null")
	private Boolean resourceModIsDelete;

}
