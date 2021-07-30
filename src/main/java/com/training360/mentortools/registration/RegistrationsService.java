package com.training360.mentortools.registration;

import com.training360.mentortools.student.Student;
import com.training360.mentortools.student.StudentNotFoundException;
import com.training360.mentortools.student.StudentRepository;
import com.training360.mentortools.trainingClass.TrainingClass;
import com.training360.mentortools.trainingClass.TrainingClassNotFoundException;
import com.training360.mentortools.trainingClass.TrainingClassRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RegistrationsService {

    private ModelMapper modelMapper;

    private RegistrationsRepository repository;
    private StudentRepository studentRepository;
    private TrainingClassRepository trainingClassRepository;

    @Transactional
    public RegistrationDto createRegistrationForStudentToATrainingClass(long id, CreateRegistrationCommand command) {
        TrainingClass trainingClass = trainingClassRepository.findById(id).orElseThrow(() -> new TrainingClassNotFoundException("Training class not found!"));
        Student student = studentRepository.findById(command.getStudentId()).orElseThrow(() -> new StudentNotFoundException("Student not found!"));
        Registration registration = new Registration(RegistrationsStatus.ACTIVE, trainingClass, student);
        repository.save(registration);
        trainingClass.addRegistration(registration);
        student.addRegistration(registration);
        return modelMapper.map(registration, RegistrationDto.class);
    }

    public List<RegistrationListDTO> listAllRegistrationOfATrainingClass(@Param("id") long id) {
        return repository.findRegisteredStudentsInATrainingClass(id);
    }

    public List<StudentsRegistrationListDTO> listAllRegistrationOfAStudent(long id) {
        return repository.findAllRegistrationsOfAStudent(id);
    }
}
