package com.sparta.marketkurlybe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class MarketkurlyBeApplication {

    public static void main(String[] args) {
        SpringApplication.run(MarketkurlyBeApplication.class, args);
    }

}
