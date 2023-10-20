package com.control.model.dto;

import com.control.model.dto.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record UserIdDTO(

		@Id @NotBlank(message = "It cannot be blank") @ExistsUserId String userId

) {

}
