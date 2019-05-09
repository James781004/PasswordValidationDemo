package com.James.demo.utils;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { PasswordValidator.class })
public @interface Password {

	String message() default "wrong pattern!!";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}
