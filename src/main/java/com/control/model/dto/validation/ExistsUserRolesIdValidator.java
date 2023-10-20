package com.control.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRolesRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserRolesIdValidator implements ConstraintValidator<ExistsUserRolesId, String> {

	private String message;

	@Autowired
	private UserRolesRepository userRolesRepository;

	@Override
	public void initialize(ExistsUserRolesId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		if (id == null || "".equals(id)) {
			return true;
		}

		if (userRolesRepository.existsById(id)) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Valid: False");
		return false;
	}

}