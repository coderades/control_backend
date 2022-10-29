package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

public class ExistsUserNameUpdateValidator implements ConstraintValidator<ExistsUserNameUpdate, UserUpdateDTO> {

	private final UserRepository userRepository;

	public ExistsUserNameUpdateValidator(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

	@Override
	public void initialize(ExistsUserNameUpdate constraintAnnotation) {
	}

	@Override
	public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(new StringBuilder().append("Conflict: UserName ")
				.append(userUpdateDTO.getUserName()).append(" is already in use").toString()).addConstraintViolation();

		return userRepository.existsByUserIdIsNotAndUserName(userUpdateDTO.getUserId(), userUpdateDTO.getUserName())
				? false
				: true;
	}
}
