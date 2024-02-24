package com.control_backend.model.dto;

import com.control_backend.model.dto.validation.ExistsUserName;

import jakarta.validation.constraints.NotBlank;

public record AuthDTO(

		@NotBlank(message = "It cannot be blank") String applicationId,
		@NotBlank(message = "It cannot be blank") @ExistsUserName String userName,
		@NotBlank(message = "It cannot be blank") String userPassword

) {

}
