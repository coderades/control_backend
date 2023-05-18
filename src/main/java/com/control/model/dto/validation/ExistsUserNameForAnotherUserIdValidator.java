package com.control.model.dto.validation;

import org.springframework.beans.BeanWrapperImpl;
import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsUserNameForAnotherUserIdValidator
		implements ConstraintValidator<ExistsUserNameForAnotherUserId, Object> {

	private String fieldUserId;
	private String fieldUserName;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserNameForAnotherUserId constraint) {
		fieldUserId = constraint.fieldUserId();
		fieldUserName = constraint.fieldUserName();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {	
		if(fieldUserId == null || fieldUserName == null) {
			return false;
		}		
		
		final var userId = new BeanWrapperImpl(object).getPropertyValue(fieldUserId).toString();
		final var userName = new BeanWrapperImpl(object).getPropertyValue(fieldUserName).toString();

		if (!userRepository.existsByUserIdIsNotAndUserName(userId, userName)) {
			log.info("True");
			return true;
		}

		final var message = new StringBuilder().append("userName ").append(userName).append(" exists for another user")
				.toString();
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		log.error("False");
		
		return false;
	}

}
