package com.control.model.dto;

import java.io.Serializable;

import com.control.model.dto.validation.ExistsRoleId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RoleUpdateDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsRoleId
	private String roleId;

	@NotNull(message = "It cannot be null")
	private Boolean roleIsEnabled;

	@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters")
	private String roleName;

}
