package com.control.model.dto;

import com.control.model.dto.validation.ExistsUserName;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(

		@NotBlank(message = "It cannot be blank") @ExistsUserName String userName,
		@NotBlank(message = "It cannot be blank") String userPassword

) {

}
