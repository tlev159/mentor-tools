package com.training360.mentortools.trainingClass;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UpdatedEndAfterStartDateValidator implements ConstraintValidator<IsUpdatedEndAfterStart, UpdateTrainingClassCommand> {

    @Override
    public boolean isValid(UpdateTrainingClassCommand command, ConstraintValidatorContext context) {
        return command.getStartDate().isBefore(command.getEndDate());
    }
}
