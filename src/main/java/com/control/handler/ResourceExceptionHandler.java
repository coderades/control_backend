package com.control.handler;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
		final Map<String, Object> body = new LinkedHashMap<>();
		body.put("status", ex.getStatusCode().value());
		body.put("error", ex.getStatusCode());
		body.put("message", ex.getMessage());
		body.put("path", ex.getBindingResult().getErrorCount());

		return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	}
}
