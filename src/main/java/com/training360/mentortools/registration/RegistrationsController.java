package com.training360.mentortools.registration;

import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationsController {

    private RegistrationsService service;

    @PostMapping("/trainingclasses/{id}/registrations")
    public RegistrationDto createRegistrationForStudentToATrainingClass(@PathVariable("id") long id, @RequestBody CreateRegistrationCommand command) {
        return service.createRegistrationForStudentToATrainingClass(id, command);
    }

    @GetMapping("/trainingclasses/{id}/registrations")
    public List<RegistrationListDTO> listAllRegistrationOfATrainingClass(@PathVariable("id") long id) {
        return service.listAllRegistrationOfATrainingClass(id);
    }

    @GetMapping("/students/{id}/registrations")
    public List<StudentsRegistrationListDTO> listAllRegistrationOfAStudent(@PathVariable("id") long id) {
        return service.listAllRegistrationOfAStudent(id);
    }

    @PutMapping("/registrations/{id}")
    public RegistrationDto updateRegistrationsStatus(@PathVariable("id") long id, @RequestParam UpdateRegistrationsStatusCommand command) {
        return service.updateRegistrationsStatus(id, command);
    }

    @ExceptionHandler(RegistrationInTrainingClassForStudentExistsException.class)
    public ResponseEntity<Problem> handleNotFound(RegistrationInTrainingClassForStudentExistsException ritcfsee) {
        Problem problem = Problem.builder()
                .withType(URI.create("registration/registration-exists"))
                .withTitle("Registration for student in this training class exists")
                .withStatus(Status.NOT_ACCEPTABLE)
                .withDetail(ritcfsee.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }

}
