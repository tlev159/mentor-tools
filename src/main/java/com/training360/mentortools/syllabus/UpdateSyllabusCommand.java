package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateSyllabusCommand {

    @NotEmpty
    @Column(nullable = false)
    private String name;

}
