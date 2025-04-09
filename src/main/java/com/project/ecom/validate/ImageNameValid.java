package com.project.ecom.validate;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ImageNameValidator.class)
public @interface ImageNameValid {

    // Default error message
    String message() default "Invalid Image Name !!";

    // Represents group of constraints
    Class<?>[] groups() default {};

    // Additional metadata info
    Class<? extends Payload>[] payload() default {};
}
