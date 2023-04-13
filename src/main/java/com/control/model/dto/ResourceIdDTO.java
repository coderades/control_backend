package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsResourceId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ResourceIdDTO implements Serializable {

	private static final long serialVersionUID = -2913729757055081606L;
	
	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsResourceId
	private String resourceId;

}
