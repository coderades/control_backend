package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.UserService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = ExistsUserName.Validator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsUserName {
	
	String message() default "Validator: userName is already in use";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsUserName, String> {

		@Autowired
		private UserService userService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (userService.existsByUserName(value)) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: userName=").append(value)
					.append(" is already in use").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}

}
