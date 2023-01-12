package com.control.model.validation;

import com.control.model.dto.UserUpdateDTO;
import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
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
		if (userRepository.existsByUserIdIsNotAndUserEmail(userUpdateDTO.getUserId(), userUpdateDTO.getUserEmail())) {
			final var message = new StringBuilder().append("Validator: userId=").append("userUpdateDTO.getUserId()")
					.append(", userEmail=").append(userUpdateDTO.getUserEmail()).append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
