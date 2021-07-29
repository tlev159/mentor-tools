package com.training360.mentortools.trainingClass;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@AllArgsConstructor
@RestController
@RequestMapping("/api")
@Tag(name = "Training 360 mentor helping app")
public class TrainingClassController {

    private TrainingClassService service;


}
