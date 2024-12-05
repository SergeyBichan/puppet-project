package com.aston.frontendpracticeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan
@SpringBootApplication
public class FrontendPracticeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(FrontendPracticeServiceApplication.class, args);
    }

}