package com.liemartt.taxigarage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jdbc.repository.config.EnableJdbcRepositories;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Component;

@SpringBootApplication(exclude = {JdbcTemplateAutoConfiguration.class})
@EnableJpaRepositories
public class TaxiGarageApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaxiGarageApplication.class, args);
    }

}
