package com.control.model.dto;
import java.io.Serializable;

import com.control.model.validation.ExistsRoleId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RoleIdDTO implements Serializable {

	private static final long serialVersionUID = -1506397500308371712L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsRoleId
	private String roleId;

}
