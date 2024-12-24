package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.Size;

public record AuthDTO(

		@Size(min = 1, max = 30, message = "Enter between 2 and 30 characters") String userName,

		@Size(min = 3, max = 30, message = "Enter between 2 and 30 characters") String userPassword

) {

}
