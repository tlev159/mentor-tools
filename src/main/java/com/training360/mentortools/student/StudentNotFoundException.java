package com.training360.mentortools.student;

public class StudentNotFoundException extends RuntimeException {

    private String message;

    public StudentNotFoundException(String message) {
        this.message = message;
    }

}
