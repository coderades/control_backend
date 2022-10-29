package com.control.model.enumerator;

public enum LogActionEnum {

	ELEMENTS("Elements:"), LOGIN("Login:"), LOGOUT("Logout:");

	private final String description;

	LogActionEnum(String description) {
		this.description = description;
	}

	public String getDescription() {
		return this.description;
	}

}
