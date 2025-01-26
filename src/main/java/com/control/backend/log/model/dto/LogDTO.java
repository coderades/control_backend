package com.control.backend.log.model.dto;

import java.time.LocalDateTime;

import com.control.backend.log.model.enumerator.LogActionEnum;
import com.control.backend.log.model.enumerator.LogLevelEnum;

public record LogDTO(

		LogLevelEnum logLevel,

		String logSession,

		LogActionEnum logAction,

		String logLogger,

		LocalDateTime logCreatedAt

) {

}
