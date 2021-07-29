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
@IsEndAfterStart
public class CreateTrainingClassCommand {

    @NotBlank
    @Schema(description = "name of the T360 class", example = "Első turnus")
    private String name;

    @Schema(description = "start date of the course")
    private LocalDate startDate;

    @Schema(description = "end date of the course")
    private LocalDate endDate;
}
