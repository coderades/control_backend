package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsApplicationId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ApplicationIdDTO implements Serializable {

	private static final long serialVersionUID = -527506195465983324L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsApplicationId
	private String applicationId;

}
