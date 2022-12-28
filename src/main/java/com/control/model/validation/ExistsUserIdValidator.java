package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.UserRepository;

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
			final var message = new StringBuilder().append("Validator: userId=").append(value).append(" does not exist")
					.toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
