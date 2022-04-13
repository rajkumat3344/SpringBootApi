package com.example.SpringBootApi.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class StudentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository studentRepository){
        return args -> {
            Student raj = new Student(
                    "Raj Dutta",
                    "rdutta@gmail.com",
                    LocalDate.of(1997, Month.NOVEMBER, 10)
            );

            Student radhika = new Student(
                    "Radhika Agrawal",
                    "agrawalr@gmail.com",
                    LocalDate.of(1998, Month.JULY, 28)
            );
            studentRepository.saveAll(
                    List.of(raj, radhika)
            );
        };
    }
}
