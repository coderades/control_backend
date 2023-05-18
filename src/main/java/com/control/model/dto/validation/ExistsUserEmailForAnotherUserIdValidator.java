package com.control.model.dto.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserEmailForAnotherUserIdValidator
		implements ConstraintValidator<ExistsUserEmailForAnotherUserId, Object> {

	private String fieldUserId;
	private String fieldUserEmail;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserEmailForAnotherUserId constraint) {
		fieldUserId = constraint.fieldUserId();
		fieldUserEmail = constraint.fieldUserEmail();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {	
		if(fieldUserId == null || fieldUserEmail == null) {
			return false;
		}		
		
		final var userId = new BeanWrapperImpl(object).getPropertyValue(fieldUserId).toString();
		final var userEmail = new BeanWrapperImpl(object).getPropertyValue(fieldUserEmail).toString();

		if (!userRepository.existsByUserIdIsNotAndUserEmail(userId, userEmail)) {
			log.info("True");
			return true;
		}

		final var message = new StringBuilder().append("userEmail ").append(userEmail).append(" exists for another user")
				.toString();
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		log.error("False");
		
		return false;
	}

}
