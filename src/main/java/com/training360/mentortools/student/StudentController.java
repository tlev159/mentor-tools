package com.training360.mentortools.student;

import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Tag(name = "Operations with students")
public class StudentController {

    private StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@Valid @RequestBody CreateStudentCommand command) {
        return service.createStudent(command);
    }

    @GetMapping
    @Operation(summary = "List all student", description = "List all student")
    public List<StudentDTO> listAll() {
        return service.listAll();
    }

    @GetMapping("/{id}")
    public StudentDTO findStudentById(@PathVariable("id") long id) {
        return service.findStudentById(id);
    }

    @PutMapping("/{id}")
    public StudentDTO updateStudentById(@PathVariable("id") long id, @Valid @RequestBody UpdateStudentCommand command) {
        return service.updateStudentById(id, command);
    }

    @DeleteMapping("/{id}")
    public void deleteStudentById(@PathVariable("id") long id) {
        service.deleteStudentById(id);
    }

    @DeleteMapping
    public void deleteAllStudent() {
        service.deleteAllStudent();
    }

    @ExceptionHandler(StudentNotFoundException.class)
    public ResponseEntity<Problem> handleNotFound(StudentNotFoundException snfe) {
        Problem problem = Problem.builder()
                .withType(URI.create("student/student-not-found"))
                .withTitle("Student not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(snfe.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
