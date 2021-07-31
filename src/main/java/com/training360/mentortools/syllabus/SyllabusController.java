package com.training360.mentortools.syllabus;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@RestController
@RequestMapping("/api/syllabus")
public class SyllabusController {

    private SyllabusService service;

    @PostMapping
    public SyllabusDTO createSyllabus(@Valid @RequestBody CreateSyllabusCommand command) {
        return service.createSyllabus(command);
    }

    @GetMapping("/{id}")
    public SyllabusDTO findSyllabusById(@PathVariable("id") long id) {
        return service.findSyllabusById(id);
    }

    @GetMapping
    public List<SyllabusDTO> listAllSyllabus(@RequestParam Optional<Long> id, @RequestParam Optional<String> name) {
        return service.listAllSyllabus(id, name);
    }

    @PutMapping("/{id}")
    public SyllabusDTO updateSyllabus(@PathVariable("id") Long id, @Valid @RequestBody UpdateSyllabusCommand command) {
        return service.updateSyllabus(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteSyllabusById(@PathVariable("id") long id) {
        service.deleteSyllabusById(id);
    }

    @DeleteMapping
    public void deleteAllSyllabus() {
        service.deleteAllSyllabus();
    }

    @ExceptionHandler(SyllabusNotFoundException.class)
    public ResponseEntity<Problem> handleNotFound(SyllabusNotFoundException snfe) {
        Problem problem = Problem.builder()
                .withType(URI.create("syllabus/syllabus-not-found"))
                .withTitle("Syllabus not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(snfe.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}
