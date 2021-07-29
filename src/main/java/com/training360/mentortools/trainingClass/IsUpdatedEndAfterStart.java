package com.training360.mentortools.trainingClass;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.Retention;

import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Retention(RUNTIME)
@Constraint(validatedBy = UpdatedEndAfterStartDateValidator.class)
public @interface IsUpdatedEndAfterStart {

    String message() default "End date must be after start date!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };

}
