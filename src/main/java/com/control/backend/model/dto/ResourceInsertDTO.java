package com.control.backend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResourceInsertDTO(

		@NotBlank(message = "It cannot be blank") String applicationId,

		@NotNull(message = "It cannot be null") Boolean resourceIsEnabled,

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String resourceName,

		@Size(min = 2, max = 500, message = "Enter between 2 and 500 characters") String resourcePath

) {

}
