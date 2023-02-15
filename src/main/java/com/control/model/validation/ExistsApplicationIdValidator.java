package com.control.model.validation;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.ApplicationService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsApplicationIdValidator implements ConstraintValidator<ExistsApplicationId, String> {

	@Autowired
	private ApplicationService applicationService;

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (applicationService.existsById(value)) {
			log.info("Validator: return=true");
			return true;
		}

		final var message = new StringBuilder().append("applicationId=").append(value).append(" does not exist")
				.toString();

		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

		log.error("Validator: return=false, message={}", message);
		return false;
	}
}
