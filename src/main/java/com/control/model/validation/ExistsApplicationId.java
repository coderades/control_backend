package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.ApplicationService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

/*
 * @see https://medium.com/codex/spring-boot-create-custom-annotation-to-validate-request-parameter-dcf483539d90
 * 
 */
@Constraint(validatedBy = { ExistsApplicationId.Validator.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsApplicationId {

	String message() default "Validator: applicationId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsApplicationId, String> {

		@Autowired
		private ApplicationService applicationService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (applicationService.existsById(value)) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: applicationId=").append(value)
					.append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}
	
}