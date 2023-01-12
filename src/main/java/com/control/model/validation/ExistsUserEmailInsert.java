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
@Constraint(validatedBy = ExistsUserEmailInsertValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface ExistsUserEmailInsert {
	String message() default "Conflict: UserEmail is already in use";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
