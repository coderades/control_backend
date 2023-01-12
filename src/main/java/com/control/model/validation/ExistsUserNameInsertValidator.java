package com.control.model.validation;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserNameInsertValidator implements ConstraintValidator<ExistsUserNameInsert, String> {

	private final UserRepository userRepository;

	public ExistsUserNameInsertValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (userRepository.existsByUserName(value)) {
			final var message = new StringBuilder().append("Conflict: userName=").append(value)
					.append(" is already in use").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
