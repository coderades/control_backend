package com.control.backend.auth.model.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ResourceDTO(

		@NotNull(message = "It cannot be null") Boolean resourceIsEnabled,
		
		@NotNull(message = "It cannot be null") Boolean resourceIsPublic,
		
		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String resourceName,
		
		@Size(min = 5, max = 800, message = "Enter between 5 and 800 characters") String resourcePath,
		
		@NotNull(message = "It cannot be null") Boolean resourceIsCreate,
		
		@NotNull(message = "It cannot be null") Boolean resourceIsRead,
		
		@NotNull(message = "It cannot be null") Boolean resourceIsUpdate,
		
		@NotNull(message = "It cannot be null") Boolean resourceIsDelete

) {

}
