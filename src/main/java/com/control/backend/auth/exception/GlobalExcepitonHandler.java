package com.control.backend.auth.exception;

import java.time.LocalDateTime;

import javax.naming.AuthenticationException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import jakarta.validation.ConstraintViolationException;

@ControllerAdvice
public class GlobalExcepitonHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExcepitonHandler.class);

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

		try {
			final var jsonObject = new JSONObject();
			jsonObject.put("title", httpStatus.getReasonPhrase());
			jsonObject.put("message", exception.getMessage());
			logger.warn("{} | HTTPSTATUS: {} | SESSION: {} | EXCEPTION: {}", LocalDateTime.now(),
					httpStatusCode >= 400 && httpStatusCode < 500 ? "WARN" : "ERROR",
					RequestContextHolder.currentRequestAttributes().getSessionId(), jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}

		return new ResponseEntity<>(problemDetail, httpStatus);
	}

}
