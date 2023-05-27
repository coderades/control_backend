package com.control.handler;

import java.util.ArrayList;
import java.util.List;

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

@RestControllerAdvice(annotations = RestController.class)
@Slf4j
public class RestExceptionHandler {

	@ExceptionHandler(ResponseStatusException.class)
	public ResponseEntity<Exception> handleNotFound(final ResponseStatusException exception) {
		final var errorResponse = new Exception();
		errorResponse.setStatus(exception.getStatusCode().value());
		errorResponse.setException(exception.getClass().getSimpleName());
		errorResponse.setMessage(exception.getMessage());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, exception.getStatusCode());
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Exception> handleMethodArgumentNotValid(final MethodArgumentNotValidException exception) {
		final List<ExceptionField> fieldErros = new ArrayList<>();

		exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
			final var fieldExtension = new ExceptionField();
			fieldExtension.setField(fieldError.getField());
			fieldExtension.setType(fieldError.getCode());
			fieldExtension.setArgument(fieldError.getDefaultMessage());
			fieldErros.add(fieldExtension);
		});

		exception.getBindingResult().getGlobalErrors().forEach(fieldError -> {
			final var fieldExtension = new ExceptionField();
			fieldExtension.setField(fieldError.getObjectName());
			fieldExtension.setType(fieldError.getCode());
			fieldExtension.setArgument(fieldError.getDefaultMessage());
			fieldErros.add(fieldExtension);
		});

		final var errorResponse = new Exception();
		errorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
		errorResponse.setException(HttpStatus.BAD_REQUEST.name());
		errorResponse.setMessage(exception.getClass().getSimpleName());
		errorResponse.setFields(fieldErros);

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(Throwable.class)
	public ResponseEntity<Exception> handleThrowable(final Throwable exception) {
		exception.printStackTrace();
		final var errorResponse = new Exception();
		errorResponse.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.value());
		errorResponse.setException(exception.getClass().getSimpleName());

		log.error(errorResponse.toString());

		return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
