package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

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


}
