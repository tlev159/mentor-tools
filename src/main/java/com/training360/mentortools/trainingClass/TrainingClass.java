package com.training360.mentortools.trainingClass;

import com.training360.mentortools.registration.Registration;
import com.training360.mentortools.student.Student;
import com.training360.mentortools.syllabus.Syllabus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "training_classes")
public class TrainingClass {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private LocalDate startDate;

    private LocalDate endDate;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "trainingClass")
    private List<Registration> registrations;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "syllabus_id")
    private Syllabus syllabus;

    public TrainingClass(String name, LocalDate startDate, LocalDate endDate) {
        this.name = name;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public void addRegistration(Registration registration) {
        if (registrations == null) {
            registrations = new ArrayList<>();
        }
        registrations.add(registration);
        registration.setTrainingClass(this);
    }

}
