package com.mano.api.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mano.api.model.Student;
import com.mano.api.service.StudentService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.time.LocalDate;
import java.time.Month;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@WebMvcTest
@AutoConfigureMockMvc
public class StudentControllerTest {

    static String STUDENT_API = "/api/v1/student";

    @Autowired
    MockMvc mvc;

    @MockBean
    StudentService service;

    @Test
    @DisplayName("Must be create a student with success")
    public void createStudentTest() throws Exception {
        Student student = new Student();
        student.setEmail("maneco@email.com");
        student.setAge(11);
        student.setDateOfBirth(LocalDate.of(1998, Month.APRIL,18));
        student.setName("maneco");
        student.setId(1l);

        BDDMockito.given(service.addNewStudent(Mockito.any(Student.class))).willReturn(student);

        String json = new ObjectMapper().writeValueAsString(student);

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post(STUDENT_API)
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON)
                .content(json);

        mvc
                .perform(request)
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.jsonPath("email").value(student.getEmail()))
                .andExpect(MockMvcResultMatchers.jsonPath("age").value(student.getAge()))
                .andExpect(MockMvcResultMatchers.jsonPath("dateOfBirth").isNotEmpty())
                .andExpect(MockMvcResultMatchers.jsonPath("name").value(student.getName()))
                .andExpect(MockMvcResultMatchers.jsonPath("id").isNotEmpty());



    }

}
