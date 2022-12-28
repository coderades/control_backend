package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.control.model.validation.ExistsUserEmailUpdate;
import com.control.model.validation.ExistsUserId;
import com.control.model.validation.ExistsUserNameUpdate;

import lombok.Data;

@Data
@ExistsUserNameUpdate
@ExistsUserEmailUpdate
public class UserDTO implements Serializable {

	private static final long serialVersionUID = 3445600659154104881L;
	
	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsUserId
	private String userId;
		
}
