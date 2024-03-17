package com.example.rendez_vous;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class RendezVousApplication {

    public static void main(String[] args) {
        SpringApplication.run(RendezVousApplication.class, args);
    }

}
