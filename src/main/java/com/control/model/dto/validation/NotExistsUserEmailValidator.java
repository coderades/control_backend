package com.control.model.dto.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.repository.UserRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class NotExistsUserEmailValidator implements ConstraintValidator<NotExistsUserEmail, String> {

	private String message;
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public void initialize(NotExistsUserEmail constraintAnnotation) {
		this.message = constraintAnnotation.message();
	}
	
	@Override
	public boolean isValid(String email, ConstraintValidatorContext context) {
		if (!userRepository.existsByUserEmail(email)) {
			log.info("True");
			return true;
		}
		
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
		
		log.error("False");		
		return false;
	}
	
}