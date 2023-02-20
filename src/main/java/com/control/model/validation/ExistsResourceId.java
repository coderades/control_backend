package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.ResourceService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = ExistsResourceId.Validator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsResourceId {
	
	String message() default "Not Found: ResourceId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	@Slf4j
	class Validator implements ConstraintValidator<ExistsResourceId, String> {

		@Autowired
		private ResourceService resourceService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (resourceService.existsById(value)) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: resourceId=").append(value).append(" does not exist")
					.toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}
	
}
