package com.control.backend.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record UserIdDTO(

		@Id @NotBlank(message = "It cannot be blank") String userId

) {

}
