package com.control.model.dto;

import java.io.Serializable;

import com.control.model.dto.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserPasswordDTO implements Serializable {

	private static final long serialVersionUID = 2536872250822949319L;

	@Id
	@NotBlank(message = "It cannot be blank")
	@ExistsUserId
	private String userId;

	@Size(min = 33, max = 37, message = "Enter between 1 and 70 characters" )
	private String userPassword;	
		
}
