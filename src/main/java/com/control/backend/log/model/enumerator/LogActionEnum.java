package com.control.backend.log.model.enumerator;

import lombok.Getter;

@Getter
public enum LogActionEnum {

	ACTION("ACTION"),
	DATABASE("DATABASE"),
	MESSAGE("MESSAGE"),
	REPONSE("REPONSE"),
	REQUEST("REQUEST");	

    private String logType;

    LogActionEnum(String logType) {
        this.logType = logType;
    }
    
}
