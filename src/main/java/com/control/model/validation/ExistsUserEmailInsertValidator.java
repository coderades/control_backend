package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserEmailInsertValidator implements ConstraintValidator<ExistsUserEmailInsert, String> {

	private final UserRepository userRepository;

	public ExistsUserEmailInsertValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (userRepository.existsByUserEmail(value)) {
			final var message = new StringBuilder().append("Validator: userEmail=").append(value)
					.append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
