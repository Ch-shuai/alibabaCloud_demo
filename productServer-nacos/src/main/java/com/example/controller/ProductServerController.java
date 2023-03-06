package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/product")
public class ProductServerController {

    @Value("${server.port}")
    public String port;

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id){
        System.out.println("查询商品id" + id);
        return "查询商品id" + id + "端口号" + port;
    }
}
