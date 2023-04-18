package com.control.model.dto.validation;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserIdValidator implements ConstraintValidator<ExistsUserId, String> {

	private final UserRepository userRepository;

	public ExistsUserIdValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (userRepository.existsById(value)) {
			log.info("Validator: return=true");
			return true;
		}

		final var message = new StringBuilder().append("userId=").append(value).append(" does not exist").toString();
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		log.warn("Validator: return=false, {}", message);
		return false;
	}
	
}