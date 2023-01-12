package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsResourceId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ResourceDTO implements Serializable {

	private static final long serialVersionUID = -2913729757055081606L;
	
	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsResourceId
	private String resourceId;

}
