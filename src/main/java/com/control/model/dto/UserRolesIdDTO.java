package com.control.model.dto;

import java.io.Serializable;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserRolesIdDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotBlank(message = "It cannot be blank")
	private String userRolesId;

}
