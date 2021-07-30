package com.training360.mentortools.syllabus;

public class SyllabusNotFoundException extends RuntimeException {

    private String message;

    public SyllabusNotFoundException(String message) {
        this.message = message;
    }
}
