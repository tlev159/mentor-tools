package com.training360.mentortools;

import com.training360.mentortools.trainingClass.CreateTrainingClassCommand;
import com.training360.mentortools.trainingClass.TrainingClassDTO;
import com.training360.mentortools.trainingClass.UpdateTrainingClassCommand;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from `mentor-tools`.`training_class`")
public class TrainingClassRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    public void createThenListClass() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        assertThat(trainingClassDTO).extracting(TrainingClassDTO::getName)
                .isEqualTo("First Test Class");

        template.postForObject("/api", new CreateTrainingClassCommand("Second Test Class", LocalDate.of(2021,02,02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        List<TrainingClassDTO> trainingClassDTOS = template.exchange("/api",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingClassDTO>>() {
                }).getBody();

        assertThat(trainingClassDTOS)
                .extracting(TrainingClassDTO::getName)
                .contains("First Test Class", "Second Test Class");

    }

    @Test
    public void updateThenFindClassById() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        long id = trainingClassDTO.getId();

        template.put("/api/trainingclass/" + id, new UpdateTrainingClassCommand("First Test2 Class", LocalDate.of(2021,02,02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        List<TrainingClassDTO> trainingClassDTOS = template.exchange("/api",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingClassDTO>>() {
                }).getBody();

        assertThat(trainingClassDTOS)
                .extracting(TrainingClassDTO::getName)
                .contains("First Test2 Class");

    }

}
