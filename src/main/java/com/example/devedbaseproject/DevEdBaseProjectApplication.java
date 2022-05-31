package com.example.devedbaseproject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;



//@SpringBootApplication
@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.example.devedbaseproject.repositories")
////        (exclude = JpaRepositoriesAutoConfiguration.class)
////@SpringBootApplication(scanBasePackages = {"boot.registration"} , exclude = JpaRepositoriesAutoConfiguration.class)
////@EnableTransactionManagement
@EntityScan(basePackages = "com.example.devedbaseproject")
public class DevEdBaseProjectApplication {

    public static void main(String[] args) {
        SpringApplication.run(DevEdBaseProjectApplication.class, args);
    }


}
