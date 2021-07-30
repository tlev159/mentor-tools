package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationListDTO {

    private Long id;

    private String name;

    private RegistrationsStatus status;

}
