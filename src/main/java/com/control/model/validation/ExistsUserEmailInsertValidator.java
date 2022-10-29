package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.UserRepository;

public class ExistsUserEmailInsertValidator implements ConstraintValidator<ExistsUserEmailInsert, String> {

	private final UserRepository userRepository;

	public ExistsUserEmailInsertValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(new StringBuilder().append("Conflict: UserEmail ").append(value)
				.append(" is already in use").toString()).addConstraintViolation();

		return userRepository.existsByUserEmail(value) ? false : true;
	}
}
