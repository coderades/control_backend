package com.control.backend.log.model.dto;

import java.time.LocalDateTime;

import com.control.backend.log.model.enumerator.LogActionEnum;
import com.control.backend.log.model.enumerator.LogLevelEnum;

import jakarta.validation.constraints.Size;

public record LogDTO(

		LogLevelEnum logLevel,

		@Size(min = 32, max = 32, message = "Enter a maximum of 30 characters") String logSession,

		LogActionEnum logAction,

		String logLogger,
		
		 LocalDateTime logCreatedAt

) {

}
