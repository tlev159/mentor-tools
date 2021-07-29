package com.training360.mentortools.trainingClass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class EndAfterStartDateValidator implements ConstraintValidator<IsEndAfterStart, CreateTrainingClassCommand> {

    @Override
    public boolean isValid(CreateTrainingClassCommand command, ConstraintValidatorContext context) {
        return command.getStartDate().isBefore(command.getEndDate());
    }
}
