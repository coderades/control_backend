package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserIdDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsUserId
	private String userId;

}
