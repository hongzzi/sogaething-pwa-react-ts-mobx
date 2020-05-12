package com.project.sogaething;


import com.project.sogaething.config.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(AppProperties.class)
public class SogaethingApplication {

    public static void main(String[] args) {
        SpringApplication.run(SogaethingApplication.class, args);
    }

}
