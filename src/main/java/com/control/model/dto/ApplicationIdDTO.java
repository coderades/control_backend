package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsApplicationId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationIdDTO implements Serializable {

	private static final long serialVersionUID = -527506195465983324L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsApplicationId
	private String applicationId;

}
