package com.training360.mentortools.trainingClass;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class TrainingClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    public TrainingClass(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

}
