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

	private String field;
	private String fieldMatch;
	private String message;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserNameForAnotherUserId constraintAnnotation) {
		this.field = constraintAnnotation.field();
		this.fieldMatch = constraintAnnotation.fieldMatch();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (!userRepository.existsByUserIdIsNotAndUserName(
				new BeanWrapperImpl(object).getPropertyValue(fieldMatch).toString(),
				new BeanWrapperImpl(object).getPropertyValue(field).toString())) {			
			log.info("True");			
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		
		log.error("False");		
		return false;
	}

}
