package com.training360.mentortools.syllabus;

import com.training360.mentortools.trainingClass.TrainingClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Syllabus {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "syllabus")
    private List<TrainingClass> trainingClasses;

    public Syllabus(String name) {
        this.name = name;
    }

    public void addTrainingClass(TrainingClass trainingClass) {
        if (trainingClasses == null) {
            trainingClasses = new ArrayList<>();
        }
        trainingClasses.add(trainingClass);
        trainingClass.setSyllabus(this);
    }
}
