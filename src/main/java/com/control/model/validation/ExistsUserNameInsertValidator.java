package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.UserRepository;

public class ExistsUserNameInsertValidator implements ConstraintValidator<ExistsUserNameInsert, String> {

	private final UserRepository userRepository;

	public ExistsUserNameInsertValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				new StringBuilder().append("Conflict: UserName ").append(value).append(" is already in use").toString())
				.addConstraintViolation();

		return userRepository.existsByUserName(value) ? false : true;
	}
}
