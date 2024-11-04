package com.control.log.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LogInsertDTO(

		@NotBlank(message = "It cannot be blank") String logUserId,

		@Size(max = 32, message = "Max 32 characters") String logSession,

		@Size(min = 4, max = 7, message = "Enter between 4 and 7 characters") String logLevel,

		@Size(min = 1, max = 800, message = "Enter between 1 and 800 characters") String logLogger,

		@Size(min = 1, max = 800, message = "Enter between 1 and 800 characters") String logMessage,

		@Size(max = 800, message = "Max 800 characters") String logException

) {

}
