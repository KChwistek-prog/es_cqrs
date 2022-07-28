package com.avra.es_cqrs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class })
public class EsCqrsApplication {

    public static void main(String[] args) {
        SpringApplication.run(EsCqrsApplication.class, args);
    }

}
