package com.training360.mentortools.registration;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RegistrationsRepository extends JpaRepository<Registration, Long> {

    @Query(value = "select new com.training360.mentortools.registration.RegistrationListDTO(s.id, s.name, r.status) from Registration r join fetch Student s on r.student=s.id where r.trainingClass.id = :id")
    List<RegistrationListDTO> findRegisteredStudentsInATrainingClass(@Param("id") long id);

}
