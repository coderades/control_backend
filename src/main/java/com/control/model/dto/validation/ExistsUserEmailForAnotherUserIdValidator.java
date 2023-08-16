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

	private String field;
	private String fieldMatch;
	private String message;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserEmailForAnotherUserId constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (!userRepository.existsByUserIdIsNotAndUserEmail(
				new BeanWrapperImpl(object).getPropertyValue(fieldMatch).toString(),
				new BeanWrapperImpl(object).getPropertyValue(field).toString())) {
			log.info("Valid: True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		
		log.error("Valid: False");
		return false;
	}

}
