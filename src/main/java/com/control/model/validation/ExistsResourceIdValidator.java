package com.control.model.validation;

import com.control.repository.ResourceRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsResourceIdValidator implements ConstraintValidator<ExistsResourceId, String> {

	private final ResourceRepository resourceRepository;

	public ExistsResourceIdValidator(ResourceRepository resourceRepository) {
		this.resourceRepository = resourceRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (resourceRepository.existsById(value)) {
			final var message = new StringBuilder().append("Validator: resourceId=").append(value)
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
