package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreateSyllabusCommand {

    private String name;

    private Long trainingClassId;

}
