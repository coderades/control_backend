package com.control.model.dto;

import java.io.Serializable;


import com.control.model.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordUpdateDTO implements Serializable {

	private static final long serialVersionUID = -8476191892573998843L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsUserId
	private String userId;

	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	
		
}
