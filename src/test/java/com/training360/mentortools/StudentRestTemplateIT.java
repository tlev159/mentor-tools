package com.training360.mentortools;

import com.training360.mentortools.student.CreateStudentCommand;
import com.training360.mentortools.student.StudentDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.test.context.jdbc.Sql;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from `mentor-tools`.`students`")
public class StudentRestTemplateIT {

    @Autowired
    TestRestTemplate template;

    @Test
    public void createThenListClass() {

        StudentDTO studentDTO =
                template.postForObject("/api/students", new CreateStudentCommand("Minta Aladár", "minta.aladar@gmail.com", "mintaA", ""), StudentDTO.class);

        assertThat(studentDTO).extracting(StudentDTO::getName)
                .isEqualTo("Minta Aladár");

        template.postForObject("/api/students", new CreateStudentCommand("Minta Béla", "minta.bela@gmail.com", "mintaB", ""), StudentDTO.class);

        List<StudentDTO> studentDTOS = template.exchange("/api/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertThat(studentDTOS)
                .hasSize(2)
                .extracting(StudentDTO::getName)
                .containsExactly("Minta Aladár", "Minta Béla");

    }

    @Test
    public void createUpdateThenFindById() {

        StudentDTO studentDTO =
                template.postForObject("/api/students", new CreateStudentCommand("Minta Eszter", "minta.eszter@gmail.com", "mintaE", ""), StudentDTO.class);

        assertThat(studentDTO).extracting(StudentDTO::getName)
                .isEqualTo("Minta Eszter");

        template.postForObject("/api/students", new CreateStudentCommand("Minta Fruzsina", "minta.fruzsina@gmail.com", "mintaF", ""), StudentDTO.class);

        List<StudentDTO> studentDTOS = template.exchange("/api/students",
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertThat(studentDTOS)
                .hasSize(2)
                .extracting(StudentDTO::getName)
                .containsExactly("Minta Eszter", "Minta Fruzsina");

    }

}
