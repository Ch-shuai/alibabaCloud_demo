package com.example.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/product")
public class ProductServerController {

    @Value("${server.port}")
    public String port;

    @RequestMapping("/get/{id}")
    public String get(@PathVariable("id") Integer id) {
        try {
            int sleeptime = new Random().nextInt(1000);
            TimeUnit.MILLISECONDS.sleep(sleeptime);
            System.out.println("查询商品id" + id + "等待时间" + sleeptime);
            return "查询商品id" + id + "端口号" + port;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
