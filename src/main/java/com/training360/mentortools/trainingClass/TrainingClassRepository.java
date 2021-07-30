package com.training360.mentortools.trainingClass;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TrainingClassRepository extends JpaRepository<TrainingClass, Long> {

    @Query(value = "select t from TrainingClass t where t.syllabus.id = :id")
    TrainingClass findBySyllabusId(@Param("id") long id);

}
