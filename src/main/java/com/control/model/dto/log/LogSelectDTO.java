package com.control.model.dto.log;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Data;

@Data
public class LogSelectDTO implements Serializable{

	private static final long serialVersionUID = 2914228161309281487L;
	
	@Id
	private String logId;

	@Size(min = 0, max = 32, message = "Enter between 0 and 32 characters")
	private String logSessionId;

	@NotNull(message = "It cannot be null")
	private LocalDateTime logCreatedStart;
	
	@NotNull(message = "It cannot be null")
	private LocalDateTime logCreatedEnd;

	@Size(min = 0, max = 5, message = "Enter between 0 and 5 characters")
	private String logLevel;

	@Size(min = 0, max = 200, message = "Enter between 0 and 200 characters")
	private String logClass;

	@Size(min = 0, max = 15, message = "Enter between 0 and 15 characters")
	private String logMethod;

	@Size(min = 0, max = 200, message = "Enter between 0 and 200 characters")
	private String logMessage;

}
