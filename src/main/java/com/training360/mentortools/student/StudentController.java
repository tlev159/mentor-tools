package com.training360.mentortools.student;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
@AllArgsConstructor
@Tag(name = "Operations with students")
public class StudentController {

    private StudentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public StudentDTO createStudent(@RequestBody CreateStudentCommand command) {
        return service.createStudent(command);
    }

    @GetMapping
    public List<StudentDTO> listAll() {
        return service.listAll();
    }

}
