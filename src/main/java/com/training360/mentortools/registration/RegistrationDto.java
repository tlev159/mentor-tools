package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {

    private Long id;

    private RegistrationsStatus status;

    private Long studentId;

    private Long classId;
}
