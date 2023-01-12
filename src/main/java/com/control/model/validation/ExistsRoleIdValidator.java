package com.control.model.validation;

import com.control.repository.RoleRepository;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ExistsRoleIdValidator implements ConstraintValidator<ExistsRoleId, String> {

	private final RoleRepository roleRepository;

	public ExistsRoleIdValidator(RoleRepository roleRepository) {
		this.roleRepository = roleRepository;
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		if (roleRepository.existsById(value)) {
			final var message = new StringBuilder().append("Validator: roleId=").append(value).append(" does not exist")
					.toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.warn("Validator: return={}, message={}", false, message);

			return false;
		}

		log.info("Validator: return={}", true);

		return true;
	}
}
