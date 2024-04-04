package com.control.backend.model.dto;

import jakarta.validation.constraints.Size;

public record ResourcePathDTO(

		@Size(min = 5, max = 500, message = "Enter between 2 and 50 characters") String resourcePath

) {

}
