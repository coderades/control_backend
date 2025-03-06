package com.control.backend.auth.exception.runtime;

public class JWTVerificationRuntimeException extends RuntimeException {

	private static final long serialVersionUID = 9115412202529691457L;

	public JWTVerificationRuntimeException(Exception exception) {
		super(exception);
	}

}
