package com.control.business.model.dto;

import java.time.LocalDateTime;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

public record HumanResourceTimesheetPeriod1StartDTO(

		@Id @NotBlank(message = "It cannot be blank") String humanResourceTimesheetId,

		@NotBlank(message = "It cannot be blank") LocalDateTime humanResourceTimesheetPeriod1Start

) {

}
