package com.training360.mentortools.trainingClass;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Training 360 mentor helping app")
public class TrainingClassController {

    private TrainingClassService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add new training class", description = "Ad new T360 class")
    public TrainingClassDTO createClass(@Valid @RequestBody CreateTrainingClassCommand command) {
        return service.createTrainingClass(command);
    }

    @GetMapping
    @Operation(summary = "List all classes", description = "List all classes")
    @ApiResponse(responseCode = "404", description = "Classes not found!")
    public List<TrainingClassDTO> listAllClasses() {
        return service.listAllClasses();
    }

    @GetMapping("/{id}")
    @Operation(summary = "Gives back a Class with a given id", description = "Gives back a Class with a given id")
    public TrainingClassDTO findClassWithName(@PathVariable("id") long id) {
        return service.findClass(id);
    }

    @ExceptionHandler(TrainingClassNotFoundException.class)
    public ResponseEntity<Problem> handleNotFound(TrainingClassNotFoundException tcnfe) {
        Problem problem = Problem.builder()
                .withType(URI.create("training_class/training-class-not-found"))
                .withTitle("Training class not found")
                .withStatus(Status.NOT_FOUND)
                .withDetail(tcnfe.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .contentType(MediaType.APPLICATION_PROBLEM_JSON)
                .body(problem);
    }
}