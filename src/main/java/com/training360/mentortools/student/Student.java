package com.training360.mentortools.student;

import com.training360.mentortools.registration.Registration;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OrderBy
    private String name;

    private String email;

    private String gitUsername;

    private String description;

    @ManyToMany
    private List<Registration> registrations;

    public Student(String name, String email, String gitUsername, String description) {
        this.name = name;
        this.email = email;
        this.gitUsername = gitUsername;
        this.description = description;
    }

    public Student(CreateStudentCommand command) {
        this.name = command.getName();
        this.email = command.getEmail();
        this.gitUsername = command.getGitUsername();
        this.description = command.getDescription();
    }
}
