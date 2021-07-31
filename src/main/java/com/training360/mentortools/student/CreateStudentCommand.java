package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateStudentCommand {

    @NotEmpty
    @Column(nullable = false, length = 255)
    private String name;

    @NotEmpty
    @Column(nullable = false, length = 255)
    private String email;

    @NotEmpty
    @Column(nullable = false, length = 255)
    private String gitUsername;

    private String description;
}
