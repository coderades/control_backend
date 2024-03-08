package com.control.backend.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.backend.repository.PermissionRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsPermissionIdValidator implements ConstraintValidator<ExistsPermissionId, String> {

	private String message;

	@Autowired
	private PermissionRepository permissionRepository;

	@Override
	public void initialize(ExistsPermissionId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		if (id == null || "".equals(id)) {
			return true;
		}

		if (permissionRepository.existsById(id)) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Valid: False");
		return false;
	}

}