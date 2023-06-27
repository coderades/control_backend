package com.control.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotExistsUserNameValidator implements ConstraintValidator<NotExistsUserName, String> {

	private String message;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(NotExistsUserName constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String name, ConstraintValidatorContext context) {
		if (!userRepository.existsByUserName(name)) {
			log.info("True");
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		
		log.error("False");		
		return false;
	}
	
}