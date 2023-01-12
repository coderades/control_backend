package com.control.model.validation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

/**
 * https://www.youtube.com/watch?v=C4rDGOne0s4
 *
 */
@Documented
@Constraint(validatedBy = ExistsUserIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface ExistsUserId {
	String message() default "Not Found: UserId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
