package com.control.business.model.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.control.business.model.enumerator.HumanResourceTimesheetMoviment;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HumanResourceTimesheetPuchItDTO(

		@NotBlank(message = "It cannot be blank") String userId,

		@NotNull(message = "It cannot be null") Integer humanResourceTimesheetPeriod,

		@NotNull(message = "It cannot be null") HumanResourceTimesheetMoviment humanResourceTimesheetMovement,

		@NotNull(message = "It cannot be null") LocalDate humanResourceTimesheetDate,

		@NotNull(message = "It cannot be null") LocalTime humanResourceTimesheetTime

) {

}
