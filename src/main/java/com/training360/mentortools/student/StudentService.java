package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class StudentService {

    private ModelMapper modelMapper;

    private StudentRepository repository;

    public StudentDTO createStudent(CreateStudentCommand command) {
        Student student = new Student(command);
        return modelMapper.map(repository.save(student), StudentDTO.class);
    }

    public List<StudentDTO> listAll() {
        return repository.findAll().stream()
                .map(s -> modelMapper.map(s, StudentDTO.class))
                .collect(Collectors.toList());
    }

}
