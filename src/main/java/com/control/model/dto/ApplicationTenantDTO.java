package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.control.model.validation.ExistsApplicationTenantId;

import lombok.Data;

@Data
public class ApplicationTenantDTO implements Serializable {

	private static final long serialVersionUID = -7941903129513300087L;
	
	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsApplicationTenantId
	private String applicationTenantId;

}
