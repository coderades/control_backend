package com.control.backend.auth.exception.runtime;

import org.springframework.http.HttpStatus;

public class NotFoundRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 9115412202529691457L;

	public NotFoundRuntimeException(Exception exception) {
		super(exception);
	}

	public HttpStatus getStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
