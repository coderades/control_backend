package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.model.dto.ApplicationUpdateDTO;
import com.control.service.ApplicationService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = { ExistsApplicationName.Validator.class })
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsApplicationName {

	String message() default "Validator: applicationName is already in use";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsApplicationName, ApplicationUpdateDTO> {

		@Autowired
		private ApplicationService applicationService;

		@Override
		public boolean isValid(ApplicationUpdateDTO applicationUpdateDTO, ConstraintValidatorContext context) {
			if (applicationService.existsByApplicationNameIsNotAndApplicationId(
					applicationUpdateDTO.getApplicationName(), applicationUpdateDTO.getApplicationId())) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: applicationName=")
					.append(applicationUpdateDTO.getApplicationName()).append(" is already in use").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}
	
}
