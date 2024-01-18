package com.control_backend.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control_backend.repository.RoleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j 
public class ExistsRoleIdValidator implements ConstraintValidator<ExistsRoleId, String> {

	private String message;

	@Autowired
	private RoleRepository RoleRepository;

	@Override
	public void initialize(ExistsRoleId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String object, ConstraintValidatorContext context) {
		if (RoleRepository.existsById(object)) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Valid: False");
		return false;
	}

}