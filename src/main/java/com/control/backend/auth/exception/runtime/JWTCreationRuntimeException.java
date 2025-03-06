package com.control.backend.auth.exception.runtime;

public class JWTCreationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 9115412202529691457L;

	public JWTCreationRuntimeException(Exception exception) {
		super(exception);
	}

}
