package com.training360.mentortools.trainingClass;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public TrainingClassDTO findClass(long id) {
        return modelMapper.map(repository.findById(id)
                .orElseThrow(() -> new TrainingClassNotFoundException("Cannot find Class!")), TrainingClassDTO.class);
    }

    @Transactional
    public TrainingClassDTO updateTrainingClass(long id, UpdateTrainingClassCommand command) {
        TrainingClass trainingClass = repository.findById(id).orElseThrow(() -> new TrainingClassNotFoundException("Training class with id (" + id + ") not found!"));
        trainingClass.setName(command.getName());
        trainingClass.setStartDate(command.getStartDate());
        trainingClass.setEndDate(command.getEndDate());
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }
}
