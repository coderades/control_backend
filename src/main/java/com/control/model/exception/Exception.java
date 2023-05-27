package com.control.model.exception;

import java.util.List;

import lombok.Data;

@Data
public class Exception {
	
	private Integer status;
	private String exception;
	private String message;
	private List<ExceptionField> fields;

}
