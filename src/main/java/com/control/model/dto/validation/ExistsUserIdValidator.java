package com.control.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserIdValidator implements ConstraintValidator<ExistsUserId, String> {

	@Autowired
	private UserRepository userRepository;

	@Override
	public boolean isValid(String object, ConstraintValidatorContext context) {
		if (userRepository.existsById(object)) {
			log.info("True");
			return true;
		}

		final var message = new StringBuilder().append("userId ").append(object).append(" does not exist").toString();
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		log.error("False");
		
		return false;
	}
	
}