package com.example.demo.student;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

@Configuration
public class studentConfig {

    @Bean
    CommandLineRunner commandLineRunner(StudentRepository repository){

        return args -> {

           student aman =  new student(

                        "muhammad aman",
                        "nadeemaman608@gmail.com",
                        LocalDate.of(2001, Month.OCTOBER, 5 )
                );

            student ali =  new student(
                    "muhammad ali",
                    "ali608@gmail.com",
                    LocalDate.of(2000, Month.OCTOBER, 5 )
            );

            repository.saveAll(

                    List.of(aman , ali)
            );

        };
    }






}
