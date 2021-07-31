package com.training360.mentortools.registration;

public class RegistrationInTrainingClassForStudentExistsException extends RuntimeException {

    private String message;

    public RegistrationInTrainingClassForStudentExistsException(String message) {
        this.message = message;
    }
}
