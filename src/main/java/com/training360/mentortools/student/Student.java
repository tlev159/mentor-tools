package com.training360.mentortools.student;

import com.training360.mentortools.registration.Registration;
import com.training360.mentortools.registration.RegistrationRepository;
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
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String email;

    private String gitUsername;

    private String description;

    public Student(String name, String email, String gitUsername, String description) {
        this.name = name;
        this.email = email;
        this.gitUsername = gitUsername;
        this.description = description;
    }
}
