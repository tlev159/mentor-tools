package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;

@AllArgsConstructor
@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {

    private SyllabusService service;

    @PostMapping
    public SyllabusDTO createSyllabus(@RequestBody CreateSyllabusCommand command) {
        return service.createSyllabus(command);
    }


}
