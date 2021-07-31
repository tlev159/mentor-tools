package com.training360.mentortools.registration;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateRegistrationsStatusCommand {

    private Long id;

    private RegistrationsStatus status;

}
