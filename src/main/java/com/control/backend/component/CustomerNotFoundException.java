package com.control.backend.component;

public class CustomerNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -792876201690804097L;

	public CustomerNotFoundException(String message) {
        super(message);
    }
}