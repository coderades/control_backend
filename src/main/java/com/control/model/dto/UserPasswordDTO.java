package com.control.model.dto;

import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class UserPasswordDTO extends UserIdDTO {

	private static final long serialVersionUID = -8476191892573998843L;

	@Size(min = 33, max = 37, message = "Enter between 1 and 70 characters" )
	private String userPassword;	
		
}
