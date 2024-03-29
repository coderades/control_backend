package com.control.backend.component;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import jakarta.persistence.EntityNotFoundException;

@RestControllerAdvice
public class ExceptionFilter {

	@ExceptionHandler(EntityNotFoundException.class)
	public ResponseEntity<?> handleError() {
		System.out.println(1);
		return ResponseEntity.notFound().build();
	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<?> handleError(MethodArgumentNotValidException ex) {
		return ResponseEntity.badRequest().body(ex.getFieldErrors().stream().map(DataValidationError::new).toList());
	}

	private record DataValidationError(String field, String mensage) {
		public DataValidationError(FieldError erro) {
			this(erro.getField(), erro.getDefaultMessage());
		}
	}

}
