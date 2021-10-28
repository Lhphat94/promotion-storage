package com.github.promotion;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication(scanBasePackages = "com.github.promotion.*")
@EnableMongoRepositories(basePackages = "com.github.promotion.*")
public class PromotionStorageApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(PromotionStorageApplication.class);
    }
    public static void main(String[] args) {
        SpringApplication.run(PromotionStorageApplication.class, args);
    }

}
