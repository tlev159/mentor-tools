package com.training360.mentortools.student;

import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    public StudentDTO findStudentById(long id) {
        return modelMapper.map(repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student(s) not found")), StudentDTO.class);
    }

    @Transactional
    public StudentDTO updateStudentById(long id, UpdateStudentCommand command) {
        Student student = repository.findById(id).orElseThrow(() -> new StudentNotFoundException("Student with id (" + id + ") not found!"));
        student.setName(command.getName());
        student.setEmail(command.getEmail());
        student.setGitUsername(command.getGitUsername());
        student.setDescription(command.getDescription());
        return modelMapper.map(student, StudentDTO.class);
    }

    public void deleteStudentById(long id) {
        repository.deleteById(id);
    }

    public void deleteAllStudent() {
        repository.deleteAll();
    }
}
