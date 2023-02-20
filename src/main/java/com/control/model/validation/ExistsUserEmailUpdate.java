package com.control.model.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.beans.factory.annotation.Autowired;

import com.control.model.dto.UserUpdateDTO;
import com.control.service.UserService;

import jakarta.validation.Constraint;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import jakarta.validation.Payload;
import lombok.extern.slf4j.Slf4j;

@Constraint(validatedBy = ExistsUserEmailUpdate.Validator.class)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsUserEmailUpdate {
	String message() default "Validator: userEmail is already in use";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
	
	@Slf4j
	class Validator implements ConstraintValidator<ExistsUserEmailUpdate, UserUpdateDTO> {

		@Autowired
		private UserService userService;

		@Override
		public boolean isValid(UserUpdateDTO userUpdateDTO, ConstraintValidatorContext context) {
			if (userService.existsByUserIdIsNotAndUserEmail(userUpdateDTO.getUserId(), userUpdateDTO.getUserEmail())) {
				log.info("Validator: true");

				return true;
			}

			final var message = new StringBuilder().append("Validator: applicationEmail=")
					.append(userUpdateDTO.getUserEmail()).append(" is already in use").toString();

			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(message).addConstraintViolation();

			log.error(message);

			return false;
		}
	}
}
