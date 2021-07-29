package com.training360.mentortools.trainingClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingClassDTO {

    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;
}
