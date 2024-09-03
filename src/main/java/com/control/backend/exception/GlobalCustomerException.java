package com.control.backend.exception;

public class GlobalCustomerException extends RuntimeException {

	private static final long serialVersionUID = -792876201690804097L;

	public GlobalCustomerException(String message) {
        super(message);
    }
}