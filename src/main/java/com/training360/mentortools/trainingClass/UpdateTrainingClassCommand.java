package com.training360.mentortools.trainingClass;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@IsUpdatedEndAfterStart
public class UpdateTrainingClassCommand {

    @NotBlank
    @Schema(description = "updated name of the T360 class", example = "Első turnus")
    private String name;

    @Schema(description = "updated start date of the course")
    private LocalDate startDate;

    @Schema(description = "updated end date of the course")
    private LocalDate endDate;
}
