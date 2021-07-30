package com.training360.mentortools.syllabus;

import com.training360.mentortools.trainingClass.TrainingClass;
import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import com.training360.mentortools.trainingClass.TrainingClassRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class SyllabusService {

    private ModelMapper modelMapper;
    private SyllabusRepository repository;

    private TrainingClassRepository trainingClassRepository;

    @Transactional
    public SyllabusDTO createSyllabus(CreateSyllabusCommand command) {
        Syllabus syllabus = new Syllabus(command.getName());
        TrainingClass trainingClass = trainingClassRepository.findById(command.getTrainingClassId()).orElseThrow(() -> new TrainingClassNotFoundException("Training class not found!!"));
        repository.save(syllabus);
        syllabus.addTrainingClass(trainingClass);
        return modelMapper.map(syllabus, SyllabusDTO.class);
    }

    public List<SyllabusDTO> listAllSyllabus() {
        return repository.findAll().stream().map(s -> modelMapper.map(s, SyllabusDTO.class)).collect(Collectors.toList());
    }
}
