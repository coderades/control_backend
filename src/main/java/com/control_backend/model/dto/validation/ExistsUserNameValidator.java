package com.control_backend.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control_backend.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserNameValidator implements ConstraintValidator<ExistsUserName, String> {

	private String message;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (name == null || "".equals(name)) {
			return true;
		}

		if (userRepository.existsByUserName(name)) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Valid: False");

		return false;
	}

}