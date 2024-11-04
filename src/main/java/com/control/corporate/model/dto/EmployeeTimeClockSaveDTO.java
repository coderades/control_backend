package com.control.corporate.model.dto;

import java.math.BigDecimal;

import com.control.corporate.model.enumerator.EmployeeTimeClockEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EmployeeTimeClockSaveDTO(

		@NotBlank(message = "It cannot be blank") String employeeId,

		@NotNull(message = "It cannot be null") Integer employeeTimeClockPeriod,

		EmployeeTimeClockEnum employeeTimeClockAction,

		String employeeTimeClockIp,

		BigDecimal employeeTimeClockLatitude,

		BigDecimal employeeTimeClockLongitude) {

}
