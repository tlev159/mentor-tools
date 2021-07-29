package com.training360.mentortools.registration;

import com.training360.mentortools.student.Student;
import com.training360.mentortools.trainingClass.TrainingClass;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "registration")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationsStatus status;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "registrations")
    private List<Student> students;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "registrations")
    private List<TrainingClass> trainingClasses;
}
