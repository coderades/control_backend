package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.UserRepository;

public class ExistsUserIdValidator implements ConstraintValidator<ExistsUserId, String> {

	private final UserRepository userRepository;

	public ExistsUserIdValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				new StringBuilder().append("Not Found: UserId ").append(value).append(" does not exist").toString())
				.addConstraintViolation();

		return userRepository.existsById(value);
	}
}
