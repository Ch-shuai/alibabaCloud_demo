package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class openFeignServer {
    public static void main(String[] args) {
        SpringApplication.run(openFeignServer.class,args);
    }
}