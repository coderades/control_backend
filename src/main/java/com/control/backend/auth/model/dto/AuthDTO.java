package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.Size;

public record AuthDTO(

		@Size(min = 1, max = 50, message = "Enter between 2 and 50 characters") String userName,

		@Size(min = 3, max = 70, message = "Enter between 2 and 70 characters") String userPassword

) {

}
