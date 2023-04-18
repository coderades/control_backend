package com.control.handler;

import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

/*
 * @see
 * https://www.youtube.com/watch?v=OSgf6z8xcGs&t=1232s
 */
@RestControllerAdvice(annotations = RestController.class)
public class RestExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<ErrorResponse> handleNotFound(final ResponseStatusException exception) {
		final var errorResponse = new ErrorResponse();
		errorResponse.setHttpStatus(exception.getStatusCode().value());
		errorResponse.setException(exception.getClass().getSimpleName());
		errorResponse.setMessage(exception.getMessage());
		return new ResponseEntity<>(errorResponse, exception.getStatusCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErrorResponse> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
		final var blindingResult = exception.getBindingResult();
		final var fieldErros = blindingResult.getFieldErrors().stream().map(error -> {
			final var fieldError = new FieldError();
			fieldError.setField(error.getField());
			fieldError.setType(error.getCode());
			fieldError.setArguments(error.getDefaultMessage());
			return fieldError;
		}).collect(Collectors.toList());

		final var errorResponse = new ErrorResponse();
		errorResponse.setHttpStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setException(exception.getClass().getSimpleName());
		errorResponse.setMessage("");
		errorResponse.setFieldErrors(fieldErros);
		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<ErrorResponse> handleThrowable(final Throwable exception) {
		exception.printStackTrace();
		final var errorResponse = new ErrorResponse();
		errorResponse.setHttpStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setException(exception.getClass().getSimpleName());
		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
