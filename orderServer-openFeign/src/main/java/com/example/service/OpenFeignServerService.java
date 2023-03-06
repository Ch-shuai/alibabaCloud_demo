package com.example.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * FeignClient
 *  value ： 远程调用的服务
 *  path ： 远程调用服务的controller中的头部url地址
 *                          @RequestMapping("/stock")
 *                          public class StockServerController {}
 */
@FeignClient(value = "stockServer-nacos",path = "stock")
public interface OpenFeignServerService {

    @RequestMapping("/reduct")
    public String reduct();
}
