package com.control_backend.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control_backend.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserIdValidator implements ConstraintValidator<ExistsUserId, String> {

	private String message;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserId constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String id, ConstraintValidatorContext context) {
		if (id == null || "".equals(id)) {
			return true;
		}

		if (userRepository.existsById(id)) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Valid: False");
		return false;
	}

}