package com.training360.mentortools.registration;

import com.training360.mentortools.student.Student;
import com.training360.mentortools.student.StudentDTO;
import com.training360.mentortools.student.StudentNotFoundException;
import com.training360.mentortools.student.StudentRepository;
import com.training360.mentortools.trainingClass.TrainingClass;
import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import com.training360.mentortools.trainingClass.TrainingClassRepository;
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
@Table(name = "registrations")
public class Registration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private RegistrationsStatus status;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "training_class_id")
    private TrainingClass trainingClass;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "students_id")
    private Student students;

    public Registration(RegistrationsStatus status, TrainingClass trainingClass, Student students) {
        this.status = status;
        this.trainingClass = trainingClass;
        this.students = students;
    }

}
