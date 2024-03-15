package com.control.backend.model.util;

import lombok.Data;

@Data
public class PostalCode {

	private String postalCode;
	private String stateId;
	private String city;
	private String neighborhood;
	private String adress;
	private String complement;

}