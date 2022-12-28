package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.control.model.validation.ExistsApplicationId;

import lombok.Data;

@Data
public class ApplicationDTO implements Serializable {

	private static final long serialVersionUID = -527506195465983324L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsApplicationId
	private String applicationId;

}
