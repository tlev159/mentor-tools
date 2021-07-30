package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Optional;

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
    public List<SyllabusDTO> listAllSyllabus(@RequestParam Optional<Long> id, @RequestParam Optional<String> name) {
        return service.listAllSyllabus(id, name);
    }

}
