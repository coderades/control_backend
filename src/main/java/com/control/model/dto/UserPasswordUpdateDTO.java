package com.control.model.dto;

import java.io.Serializable;

import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.control.model.validation.ExistsUserId;

import lombok.Data;

@Data
public class UserPasswordUpdateDTO implements Serializable {

	private static final long serialVersionUID = -8476191892573998843L;

	@Id
	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@ExistsUserId
	private String userId;

	@NotNull(message = "It cannot be null")
	@NotBlank(message = "It cannot be empty")
	@Size(min = 1, max = 70, message = "Enter between 1 and 70 characters" )
	private String userPassword;	
		
}
