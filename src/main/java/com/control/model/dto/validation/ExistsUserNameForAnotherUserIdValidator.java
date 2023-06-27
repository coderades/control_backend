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

	private String fieldId;
	private String fieldName;
	private String message;

	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(ExistsUserNameForAnotherUserId constraintAnnotation) {
		this.fieldName = constraintAnnotation.fieldName();
		this.fieldId = constraintAnnotation.fieldId();
		this.message = constraintAnnotation.message();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		if (!userRepository.existsByUserIdIsNotAndUserName(
				new BeanWrapperImpl(object).getPropertyValue(fieldId).toString(),
				new BeanWrapperImpl(object).getPropertyValue(fieldName).toString())) {
			log.info("True");
			return true;
		}

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("False");
		return false;
	}

}
