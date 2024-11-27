package com.ch.cryptotracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
public class CryptoTrackerApplication {

    public static void main(String[] args) {
        SpringApplication.run(CryptoTrackerApplication.class, args);
    }
}
