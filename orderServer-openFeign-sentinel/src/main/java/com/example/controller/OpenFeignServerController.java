package com.example.controller;

import com.example.service.OpenFeignServerService;
import com.example.service.ProductServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/openFeign")
public class OpenFeignServerController {

    @Autowired
    OpenFeignServerService openFeignServerService;
    @Autowired
    ProductServerService productServerService;

    @RequestMapping("/test")
    public String openFeignServerDemo() {
//        String reduct = openFeignServerService.reduct();

        String productServerDemo = productServerService.ProductServerDemo(111);
        return "hello openFeign" + productServerDemo;
    }
}
