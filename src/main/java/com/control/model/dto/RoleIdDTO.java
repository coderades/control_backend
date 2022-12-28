package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.control.model.validation.ExistsRoleId;

import lombok.Data;

@Data
public class RoleIdDTO implements Serializable {

	private static final long serialVersionUID = -1506397500308371712L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsRoleId
	private String roleId;

}
