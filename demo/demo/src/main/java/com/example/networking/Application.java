package com.example.networking;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@SpringBootApplication
@ComponentScan(basePackages = "com.example.SharedControllers")
@ComponentScan(basePackages = "com.example.networking")
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);

    }
}