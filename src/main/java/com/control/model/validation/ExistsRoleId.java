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
@Constraint(validatedBy = ExistsRoleIdValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD, ElementType.PARAMETER })
public @interface ExistsRoleId {
	String message() default "Not Found: RoleId does not exist";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
