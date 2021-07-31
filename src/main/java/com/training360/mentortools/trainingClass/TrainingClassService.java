package com.training360.mentortools.trainingClass;

import com.training360.mentortools.registration.CreateRegistrationCommand;
import com.training360.mentortools.student.Student;
import com.training360.mentortools.student.StudentNotFoundException;
import com.training360.mentortools.student.StudentRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class TrainingClassService {

    private ModelMapper modelMapper;

    private TrainingClassRepository repository;
    private StudentRepository studentRepository;

    public TrainingClassDTO createTrainingClass(CreateTrainingClassCommand command) {
        TrainingClass trainingClass = new TrainingClass(command.getName(), command.getStartDate(), command.getEndDate());
        repository.save(trainingClass);
        return modelMapper.map(trainingClass, TrainingClassDTO.class);
    }

    public List<TrainingClassDTO> listAllClasses(Optional<String> name, Optional<LocalDate> startDate, Optional<LocalDate> endDate) {
        return repository.findAll().stream()
                .filter(c -> name.isEmpty() || c.getName().equalsIgnoreCase(name.get()))
                .filter(c -> startDate.isEmpty()|| c.getStartDate().isAfter(startDate.get()))
                .filter(c -> endDate.isEmpty() || c.getEndDate().isAfter(endDate.get()))
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

    public void deleteTrainingClassById(long id) {
        repository.deleteById(id);
    }

}
