package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record ApplicationUpdateDTO(

		@Id @NotBlank(message = "It cannot be blank") String applicationId,

		@NotNull(message = "It cannot be null") Boolean applicationIsEnabled,

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String applicationName,

		@Size(min = 2, max = 50, message = "Enter between 2 and 50 characters") String applicationEmail,

		@NotBlank(message = "It cannot be blank") String applicationPublicKey,
		
		@NotBlank(message = "It cannot be blank") String applicationSecretKey,
		
		@NotBlank(message = "It cannot be blank") String applicationTokenPassPhrase,
		
		@NotNull(message = "It cannot be null") Integer applicationTokenExpiration
		
) {

}
