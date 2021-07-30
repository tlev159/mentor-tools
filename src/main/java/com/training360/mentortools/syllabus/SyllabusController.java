package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {

    private SyllabusService service;

    @PostMapping
    public SyllabusDTO createSyllabus(@RequestBody CreateSyllabusCommand command) {
        return service.createSyllabus(command);
    }

    @GetMapping
    public List<SyllabusDTO> listAllSyllabus() {
        return service.listAllSyllabus();
    }

}
