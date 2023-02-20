package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.ApplicationTenantService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = { ExistsApplicationTenantId.Validator.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsApplicationTenantId {

	String message() default "Validator: applicationTenantId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsApplicationId, String> {

		@Autowired
		private ApplicationTenantService applicationTenantService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (applicationTenantService.existsById(value)) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: applicationTenantId=").append(value)
					.append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}
	
}
