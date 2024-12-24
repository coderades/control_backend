package com.control.backend.auth.exception;

public class UnauthorizedException extends RuntimeException {

	private static final long serialVersionUID = 2110384463463617673L;

	public UnauthorizedException(Exception exception) {
		super(exception);
	}
	
}