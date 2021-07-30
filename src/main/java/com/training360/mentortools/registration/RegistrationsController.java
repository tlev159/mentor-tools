package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RegistrationsController {

    private RegistrationsService service;

    @PostMapping("/trainingclasses/{id}/registrations")
    public RegistrationDto createRegistrationForStudentToATrainingClass(@PathVariable("id") long id, @RequestBody CreateRegistrationCommand command) {
        return service.createRegistrationForStudentToATrainingClass(id, command);
    }


}
