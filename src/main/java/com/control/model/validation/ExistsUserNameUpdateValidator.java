package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		if (userRepository.existsByUserIdIsNotAndUserName(userUpdateDTO.getUserId(), userUpdateDTO.getUserName())) {
			final var message = new StringBuilder().append("Validator: userId=").append("userUpdateDTO.getUserId()")
					.append(", userName=").append(userUpdateDTO.getUserName()).append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
