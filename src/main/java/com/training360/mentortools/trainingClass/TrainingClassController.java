package com.training360.mentortools.trainingClass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Training 360 mentor helping app")
public class TrainingClassController {

    private TrainingClassService service;

    @PostMapping
    @Operation(summary = "Add new training class", description = "Ad new T360 class")
    public TrainingClassDTO createClass(@Valid @RequestBody CreateTrainingClassCommand command) {
        return service.createTrainingClass(command);
    }

}
