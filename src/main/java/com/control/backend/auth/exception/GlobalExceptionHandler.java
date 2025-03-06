package com.control.backend.auth.exception;

import java.time.LocalDateTime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.RequestContextHolder;

@RestControllerAdvice
public class GlobalExceptionHandler {

	private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

	@ExceptionHandler(Exception.class)
	public ResponseEntity<?> exception(Exception exception) {
		return switch (exception.getClass().getSimpleName()) {
		case "JWTCreationRuntimeException" -> exceptionTemplate(HttpStatus.UNAUTHORIZED, exception);
		case "JWTVerificationRuntimeException" -> exceptionTemplate(HttpStatus.UNAUTHORIZED, exception);
		case "NotFoundRuntimeException" -> exceptionTemplate(HttpStatus.NOT_FOUND, exception);
		default -> throw new IllegalArgumentException("Unexpected value: " + exception.getClass().getSimpleName());
		};
	}

	private ResponseEntity<?> exceptionTemplate(HttpStatus httpStatus, Exception exception) {
		final var problemDetail = ProblemDetail.forStatusAndDetail(httpStatus, exception.getLocalizedMessage());
		problemDetail.setTitle(httpStatus.getReasonPhrase());
		problemDetail.setDetail(exception.getMessage());
		log(httpStatus, exception);
		return new ResponseEntity<>(problemDetail, httpStatus);
	}

	private void log(HttpStatus httpStatus, Exception exception) {
		try {
			final var jsonObject = new JSONObject();
			jsonObject.put("title", httpStatus.getReasonPhrase());
			jsonObject.put("message", exception.getMessage());
			logger.warn("{} | HTTPSTATUS: {} | SESSION: {} | EXCEPTION: {}", LocalDateTime.now(),
					httpStatus.value() >= 400 && httpStatus.value() < 500 ? "WARN" : "ERROR",
					RequestContextHolder.currentRequestAttributes().getSessionId(), jsonObject);
		} catch (JSONException e) {
			e.printStackTrace();
		}
	}

}
