package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "product-service" , path = "product")
public interface ProductServerService {

    @RequestMapping("/get/{id}")
    public String ProductServerDemo(@PathVariable("id") Integer id);
}
