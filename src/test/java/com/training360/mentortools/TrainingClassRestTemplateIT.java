package com.training360.mentortools;

import com.training360.mentortools.trainingClass.CreateTrainingClassCommand;
import com.training360.mentortools.trainingClass.TrainingClassDTO;
import com.training360.mentortools.trainingClass.UpdateTrainingClassCommand;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;
import org.zalando.problem.Problem;
import org.zalando.problem.Status;

import java.net.URI;
import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from `mentor-tools`.`training_classes`")
public class TrainingClassRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {
        template.delete("/api/trainingclass");
    }

    @Test
    void createTwoThenFindClassByName() {

        template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("Second Test Class", LocalDate.of(2021, 02, 02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        String searchedTrainingClassName = "Second Test Class";

        List<TrainingClassDTO> trainingClassDTOs = template.exchange("/api/trainingclass?name=" + searchedTrainingClassName,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingClassDTO>>() {
                }).getBody();

        assertThat(trainingClassDTOs)
                .hasSize(1)
                .extracting(TrainingClassDTO::getName)
                .containsExactly("Second Test Class");
    }

    @Test
    void createThenListClass() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        assertThat(trainingClassDTO).extracting(TrainingClassDTO::getName)
                .isEqualTo("First Test Class");

        template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("Second Test Class", LocalDate.of(2021, 02, 02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        List<TrainingClassDTO> trainingClassDTOS = template.exchange("/api/trainingclass",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingClassDTO>>() {
                }).getBody();

        assertThat(trainingClassDTOS)
                .extracting(TrainingClassDTO::getName)
                .contains("First Test Class", "Second Test Class");

    }

    @Test
    void updateThenFindClassById() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        long id = trainingClassDTO.getId();

        template.put("/api/trainingclass/" + id, new UpdateTrainingClassCommand("First Test2 Class", LocalDate.of(2021, 02, 02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        TrainingClassDTO loadedClass = template.getForObject("/api/trainingclass/" + id,
                TrainingClassDTO.class);

        assertThat(loadedClass)
                .extracting(TrainingClassDTO::getName)
                .isEqualTo("First Test2 Class");

    }

    @Test
    void deleteThenNotFindClassById() {

        TrainingClassDTO trainingClassDTO =
                template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("First Test Class", LocalDate.of(2021, 01, 01), LocalDate.of(2021, 03, 01)), TrainingClassDTO.class);

        long id = trainingClassDTO.getId();

        template.postForObject("/api/trainingclass", new CreateTrainingClassCommand("Second Test Class", LocalDate.of(2021, 02, 02), LocalDate.of(2021, 04, 04)), TrainingClassDTO.class);

        template.delete("/api/trainingclass/" + id);

        List<TrainingClassDTO> trainingClassDTOS = template.exchange("/api/trainingclass",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<TrainingClassDTO>>() {
                }).getBody();

        assertThat(trainingClassDTOS)
                .hasSize(1)
                .extracting(TrainingClassDTO::getName)
                .doesNotContainSequence("First Test Class")
                .containsExactly("Second Test Class");
    }

    @Test
    void createTrainingClassWithEmptyClassName() {
        Problem result = template.postForObject("/api/trainingclass",
                new CreateTrainingClassCommand("", LocalDate.of(2021, 5, 5), LocalDate.of(2021, 8, 9)), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    void createTrainingClassWithEndDateBeforeStartDate() {
        Problem result = template.postForObject("/api/trainingclass",
                new CreateTrainingClassCommand("Junior Java", LocalDate.of(2021, 5, 5), LocalDate.of(2021, 1, 1)), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    void NotFoundTrainingClassException4xx() {

        Problem result = template.getForObject("/api/trainingclass/99", Problem.class);

        assertEquals(Status.NOT_FOUND, result.getStatus());
        assertEquals(URI.create("training_class/training-class-not-found"), result.getType());

    }

}
