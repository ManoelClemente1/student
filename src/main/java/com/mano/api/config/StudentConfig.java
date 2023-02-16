package com.mano.api.config;


import com.mano.api.model.Student;
import com.mano.api.repository.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){
        return args -> {
             Student maneco = new Student(
                    "Maneco",
                    "maneco@gmail.com",
                    LocalDate.of(2000, Month.APRIL,5)
            );

            repository.save(maneco);
        };
    }

}
