package com.training360.mentortools.trainingClass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Training 360 mentor helping app")
public class TrainingClassController {

    private TrainingClassService service;

    @PostMapping
    @Operation(summary = "Add new training class", description = "Ad new T360 class")
    public TrainingClassDTO createClass(@RequestBody CreateTrainingClassCommand command) {
        return service.createTrainingClass(command);
    }
}
