package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.service.RoleService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = ExistsRoleId.Validator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsRoleId {

	String message() default "Validator: roleId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

	@Slf4j
	class Validator implements ConstraintValidator<ExistsRoleId, String> {

		@Autowired
		private RoleService roleService;

		@Override
		public boolean isValid(String value, ConstraintValidatorContext context) {
			if (roleService.existsById(value)) {
				log.info("Validator: return=true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: roleId=").append(value).append(" does not exist")
					.toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}

}
