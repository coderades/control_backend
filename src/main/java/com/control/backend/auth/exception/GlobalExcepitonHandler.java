package com.control.backend.auth.exception;

import javax.naming.AuthenticationException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExcepitonHandler {

	@ExceptionHandler({ AuthenticationException.class, ConstraintViolationException.class,
			IllegalArgumentException.class, MethodArgumentNotValidException.class,
			MethodArgumentTypeMismatchException.class, NullPointerException.class })
	public ResponseEntity<?> badRequest(Exception exception) {
		return exceptionTemplate(exception, 400);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<?> unauthorized(Exception exception) {
		return exceptionTemplate(exception, 401);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> internalServerError(Exception exception) {
		return exceptionTemplate(exception, 500);
	}

	private ResponseEntity<?> exceptionTemplate(Exception exception, int httpStatusCode) {
		final var httpStatus = HttpStatus.valueOf(httpStatusCode);
		final var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getMessage());
		problemDetail.setTitle(httpStatus.getReasonPhrase());
		problemDetail.setDetail(exception.getMessage());
		return new ResponseEntity<>(problemDetail, httpStatus);
	}

}
