package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping
@RestController
@RefreshScope
public class ConfigController {

    @Value("${user.name}")
    public String username;

    @Value("${user.age}")
    public String age;



}
