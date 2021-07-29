package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateStudentCommand {

    private String name;

    private String email;

    private String gitUsername;

    private String description;

}
