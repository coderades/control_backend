package com.control.corporate.model.dto;

import java.math.BigDecimal;

import com.control.corporate.model.enumerator.HumanResourceTimeClockActionEnum;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record HumanResourceTimeClockSaveDTO(

		@NotBlank(message = "It cannot be blank") String employeeId,

		@NotNull(message = "It cannot be null") Integer humanResourceTimeClockPeriod,

		HumanResourceTimeClockActionEnum humanResourceTimeClockAction,

		String humanResourceTimeClockIp,

		BigDecimal humanResourceTimeClockLatitude,

		BigDecimal humanResourceTimeClockLongitude) {

}
