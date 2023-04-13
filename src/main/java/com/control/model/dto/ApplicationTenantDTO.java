package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsApplicationTenantId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class ApplicationTenantDTO implements Serializable {

	private static final long serialVersionUID = -7941903129513300087L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsApplicationTenantId
	private String applicationTenantId;

}
