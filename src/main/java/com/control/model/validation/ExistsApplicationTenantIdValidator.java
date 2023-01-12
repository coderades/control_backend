package com.control.model.validation;

import com.control.repository.ApplicationTenantRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsApplicationTenantIdValidator implements ConstraintValidator<ExistsApplicationTenantId, String> {

	private final ApplicationTenantRepository applicationTenantRepository;

	public ExistsApplicationTenantIdValidator(ApplicationTenantRepository applicationTenantRepository) {
		this.applicationTenantRepository = applicationTenantRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (applicationTenantRepository.existsById(value)) {
			final var message = new StringBuilder().append("Validator: applicationTenantId=").append(value)
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
