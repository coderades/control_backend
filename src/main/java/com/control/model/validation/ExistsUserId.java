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

@Constraint(validatedBy = ExistsUserId.Validator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsUserId {
	String message() default "Validator: userId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsUserId, String> {

		@Autowired
		private UserService userService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (userService.existsById(value)) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: userId=").append(value).append(" does not exist")
					.toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}

}
