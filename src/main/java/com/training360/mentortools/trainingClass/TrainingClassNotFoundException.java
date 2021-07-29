package com.training360.mentortools.trainingClass;

public class TrainingClassNotFoundException extends RuntimeException {

    private String message;

    public TrainingClassNotFoundException(String message) {
        this.message = message;
    }
}
