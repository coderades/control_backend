package com.control.model.dto;

import com.control.model.dto.validation.ExistsUserId;

import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserUpdatePasswordDTO(@Id @NotBlank(message = "It cannot be blank") @ExistsUserId String userId,
		@Size(min = 4, max = 70, message = "Enter between 4 and 70 characters") String userPassword) {

	public UserUpdatePasswordDTO(String nome, String password, String a) {
		this(nome, password);
	}
	
}
