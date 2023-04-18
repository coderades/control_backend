package com.control.handler;

import lombok.Data;

@Data
public class FieldError {
	
	private String field;
	private String type;
	private String arguments;

}
