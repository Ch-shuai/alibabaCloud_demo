package com.example.stockServer.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/stock")
public class StockServerController {

    @GetMapping("/reduct")
    public String reduct(){
        System.out.println("扣减库存");
        return "Hello World！！！！！";
    }
}