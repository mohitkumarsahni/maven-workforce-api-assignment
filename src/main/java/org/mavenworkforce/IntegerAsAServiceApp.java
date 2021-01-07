package org.mavenworkforce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.config.EnableMongoAuditing;

@SpringBootApplication
@EnableMongoAuditing
@ComponentScan(basePackages = "org.mavenworkforce.*")
public class IntegerAsAServiceApp {
    public static void main(String[] args) {
        SpringApplication.run(IntegerAsAServiceApp.class, args);
    }
}
