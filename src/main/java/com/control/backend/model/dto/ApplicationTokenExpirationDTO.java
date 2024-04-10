package com.control.backend.model.dto;

import jakarta.validation.constraints.NotNull;

public record ApplicationTokenExpirationDTO(

		@NotNull(message = "It cannot be null") Integer applicationTokenExpiration

) {

}
