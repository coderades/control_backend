package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

public class ExistsUserEmailUpdateValidator implements ConstraintValidator<ExistsUserEmailUpdate, UserUpdateDTO> {

	private final UserRepository userRepository;

	public ExistsUserEmailUpdateValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void initialize(ExistsUserEmailUpdate constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(new StringBuilder().append("Conflict: UserEmail ")
				.append(userUpdateDTO.getUserEmail()).append(" is already in use").toString()).addConstraintViolation();

		return userRepository.existsByUserIdIsNotAndUserEmail(userUpdateDTO.getUserId(), userUpdateDTO.getUserEmail())
				? false
				: true;
	}
}
