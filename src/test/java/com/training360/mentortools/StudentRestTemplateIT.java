package com.training360.mentortools;

import com.training360.mentortools.student.CreateStudentCommand;
import com.training360.mentortools.student.StudentDTO;
import com.training360.mentortools.student.UpdateStudentCommand;
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
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql(statements = "delete from `mentor-tools`.`students`")
public class StudentRestTemplateIT {

    private final String URL_FOR_QUERY = "/api/students";

    @Autowired
    TestRestTemplate template;

    @BeforeEach
    void init() {

        template.delete(URL_FOR_QUERY);
    }

    @Test
    public void createStudentThenListAll() {

        StudentDTO studentDTO =
                template.postForObject(URL_FOR_QUERY, new CreateStudentCommand("Minta Aladár", "minta.aladar@gmail.com", "mintaA", ""), StudentDTO.class);

        assertThat(studentDTO).extracting(StudentDTO::getName)
                .isEqualTo("Minta Aladár");

        template.postForObject(URL_FOR_QUERY, new CreateStudentCommand("Minta Béla", "minta.bela@gmail.com", "mintaB", ""), StudentDTO.class);

        List<StudentDTO> studentDTOS = template.exchange(URL_FOR_QUERY,
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
    public void createUpdateThenFindStudentById() {

        StudentDTO studentDTO =
                template.postForObject(URL_FOR_QUERY, new CreateStudentCommand("Minta Eszter", "minta.eszter@gmail.com", "mintaE", ""), StudentDTO.class);

        long id = studentDTO.getId();

        assertThat(studentDTO).extracting(StudentDTO::getName)
                .isEqualTo("Minta Eszter");


        template.put(URL_FOR_QUERY + "/" +id, new UpdateStudentCommand("Minta Fruzsina", "minta.fruzsina@gmail.com", "mintaF", ""), StudentDTO.class);

        StudentDTO loadedStudent = template.getForObject(URL_FOR_QUERY + "/" +id, StudentDTO.class);

        assertThat(loadedStudent)
                .extracting(StudentDTO::getName)
                .isEqualTo("Minta Fruzsina");

    }

    @Test
    public void createTwoDeleteOneByIdThenNotFindStudentById() {

        StudentDTO studentDTO =
                template.postForObject(URL_FOR_QUERY, new CreateStudentCommand("Minta Eszter", "minta.eszter@gmail.com", "mintaE", ""), StudentDTO.class);

        long id = studentDTO.getId();

        template.postForObject(URL_FOR_QUERY, new CreateStudentCommand("Minta Fruzsina", "minta.fruzsina@gmail.com", "mintaF", ""), StudentDTO.class);

        template.delete(URL_FOR_QUERY + "/" + id);

        List<StudentDTO> studentDTOS = template.exchange(URL_FOR_QUERY,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<List<StudentDTO>>() {
                }).getBody();

        assertThat(studentDTOS)
                .hasSize(1)
                .extracting(StudentDTO::getName)
                .containsExactly("Minta Fruzsina");

    }

    @Test
    void createStudentWithEmptyClassName() {
        Problem result = template.postForObject(URL_FOR_QUERY,
                new CreateStudentCommand("", "blank@email.com", "sampleGit", ""), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    void createStudentWithEmptyEmailTest() {
        Problem result = template.postForObject(URL_FOR_QUERY,
                new CreateStudentCommand("Sample Jane", "", "janeGit", ""), Problem.class);

        assertEquals(Status.BAD_REQUEST, result.getStatus());
    }

    @Test
    void NotFoundTrainingClassException4xx() {

        Problem result = template.getForObject(URL_FOR_QUERY +"/999", Problem.class);

        assertEquals(Status.NOT_FOUND, result.getStatus());
        assertEquals(URI.create("student/student-not-found"), result.getType());

    }

}
