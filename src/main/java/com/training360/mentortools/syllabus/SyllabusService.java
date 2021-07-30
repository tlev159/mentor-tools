package com.training360.mentortools.syllabus;

import com.training360.mentortools.trainingClass.TrainingClass;
import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import com.training360.mentortools.trainingClass.TrainingClassRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
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

    public List<SyllabusDTO> listAllSyllabus(Optional<Long> id, Optional<String> name) {
        return repository.findAll().stream()
                .filter(s -> id.isEmpty() || s.getId() == id.get())
                .filter(s -> name.isEmpty() || s.getName().equalsIgnoreCase(name.get()))
                .map(s -> modelMapper.map(s, SyllabusDTO.class)).collect(Collectors.toList());
    }

    @Transactional
    public SyllabusDTO updateSyllabus(Long id, UpdateSyllabusCommand command) {
        Syllabus syllabus = repository.findById(id).orElseThrow(() -> new SyllabusNotFoundException("Syllabus not found!"));
        syllabus.setName(command.getName());
        return modelMapper.map(syllabus, SyllabusDTO.class);
    }

    public void deleteSyllabusById(long id) {
        TrainingClass trainingClass = trainingClassRepository.findBySyllabusId(id);
        trainingClass.setSyllabus(null);
        repository.deleteById(id);
    }
}
