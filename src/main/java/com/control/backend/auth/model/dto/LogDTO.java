package com.control.backend.auth.model.dto;

public record LogDTO(
		
		Long userId,
		
		String logLevel,

		String logSession,

		String logAction,

		String logLogger

) {

}
