package com.control.model.dto;

import java.io.Serializable;

import com.control.model.validation.ExistsUserEmailUpdate;
import com.control.model.validation.ExistsUserId;
import com.control.model.validation.ExistsUserName;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
@ExistsUserName
@ExistsUserEmailUpdate
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsUserId
	private String userId;

}
