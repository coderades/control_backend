package com.control.model.validation;

import com.control.repository.ApplicationRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsApplicationIdValidator implements ConstraintValidator<ExistsApplicationId, String> {

	private final ApplicationRepository applicationRepository;

	public ExistsApplicationIdValidator(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (applicationRepository.existsById(value)) {
			final var message = new StringBuilder().append("Validator: applicationId=").append(value)
					.append(" does not exist").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
