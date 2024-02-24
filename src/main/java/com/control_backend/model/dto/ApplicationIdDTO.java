package com.control_backend.model.dto;

import com.control_backend.model.dto.validation.ExistsApplicationId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record ApplicationIdDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsApplicationId String applicationId

) {

}
