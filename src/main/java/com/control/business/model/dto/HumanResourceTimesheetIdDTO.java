package com.control.business.model.dto;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record HumanResourceTimesheetIdDTO(

		@Id @NotBlank(message = "It cannot be blank") String humanResourceTimesheetId

) {

}
