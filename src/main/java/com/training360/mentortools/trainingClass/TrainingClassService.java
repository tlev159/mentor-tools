package com.training360.mentortools.trainingClass;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class TrainingClassService {

    private ModelMapper modelMapper;

    private TrainingClassRepository repository;

}
