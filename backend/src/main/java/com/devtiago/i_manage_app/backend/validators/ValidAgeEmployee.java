package com.devtiago.i_manage_app.backend.validators;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidAgeEmployee {
    String message() default "Age must be between 18 and 67";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
