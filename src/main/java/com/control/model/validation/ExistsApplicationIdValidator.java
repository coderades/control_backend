package com.control.model.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.control.repository.ApplicationRepository;

public class ExistsApplicationIdValidator implements ConstraintValidator<ExistsApplicationId, String> {

	private final ApplicationRepository applicationRepository;

	public ExistsApplicationIdValidator(ApplicationRepository applicationRepository) {
		this.applicationRepository = applicationRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		context.disableDefaultConstraintViolation();
		context.buildConstraintViolationWithTemplate(
				new StringBuilder().append("Not Found: ApplicationId ").append(value).append(" does not exist").toString())
				.addConstraintViolation();

		return applicationRepository.existsById(value);
	}
}
