package com.control.backend.log.model.enumerator;

import lombok.Getter;

@Getter
public enum LogLevelEnum {

	DEBUG("DEBUG"),
	INFO("INFO"),
	WARN("WARN"),
	ERROR("ERROR"),
	FATAL("FATAL");	

    private String logLevel;

    LogLevelEnum(String logLevel) {
        this.logLevel = logLevel;
    }
    
}
