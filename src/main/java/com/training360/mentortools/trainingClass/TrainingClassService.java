package com.training360.mentortools.trainingClass;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TrainingClassService {

    private ModelMapper modelMapper;

    private TrainingClassRepository repository;

    public TrainingClassDTO createTrainingClass(CreateTrainingClassCommand command) {
        TrainingClass trainingClass = new TrainingClass(command.getName(), command.getStartDate(), command.getEndDate());
        repository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }

    public List<TrainingClassDTO> listAllClasses() {
        return repository.findAll().stream()
                .map(c -> modelMapper.map(c, TrainingClassDTO.class))
                .collect(Collectors.toList());
    }


}
