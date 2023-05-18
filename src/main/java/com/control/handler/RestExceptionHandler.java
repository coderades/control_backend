package com.control.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import com.control.model.exception.Exception;
import com.control.model.exception.ExceptionField;

import lombok.extern.slf4j.Slf4j;

/*
 * @see
 * https://www.youtube.com/watch?v=OSgf6z8xcGs&t=1232s
 */
@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Exception> handleNotFound(final ResponseStatusException exception) {
		final var errorResponse = new Exception();		
		errorResponse.setStatus(exception.getStatusCode().value());
		errorResponse.setError(exception.getClass().getSimpleName());
		errorResponse.setMessage(exception.getMessage());
		
		log.error(errorResponse.toString());
		
		return new ResponseEntity<>(errorResponse, exception.getStatusCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Exception> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
		final var fieldErros = exception.getBindingResult().getAllErrors().stream().map(error -> {
			final var fieldError = new ExceptionField();
			fieldError.setField(error.getClass().getFields().toString());
			fieldError.setType(error.getCode());
			fieldError.setArgument(error.getDefaultMessage());
			return fieldError;
		}).collect(Collectors.toList());

		final var errorResponse = new Exception();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setError(exception.getClass().getSimpleName());
		errorResponse.setMessage("Error");
		errorResponse.setFields(fieldErros);
		
		log.error(errorResponse.toString());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Exception> handleThrowable(final Throwable exception) {
		exception.printStackTrace();
		final var errorResponse = new Exception();
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setError(exception.getClass().getSimpleName());
		
		log.error(errorResponse.toString());
		
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
