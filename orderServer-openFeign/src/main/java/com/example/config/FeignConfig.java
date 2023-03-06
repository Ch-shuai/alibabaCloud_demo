package com.example.config;

import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * feign的全剧变量
 */
@Configuration
public class FeignConfig {

    @Bean
    public Logger.Level feignLogger(){
        return Logger.Level.FULL;
    }
}
