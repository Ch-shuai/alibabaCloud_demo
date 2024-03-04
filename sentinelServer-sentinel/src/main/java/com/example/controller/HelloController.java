package com.example.controller;

import com.alibaba.csp.sentinel.EntryType;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.nacos.common.utils.ExceptionUtil;
import com.example.service.Uservice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping("/hello")
public class HelloController {

    @Autowired
    Uservice uservice;

    /**
     * 使用dashboard配置流控,【直接拒绝、冷启动、排队】
     * @SentinelResource 来标记需要进行限流保护的方法，用于标记需要进行熔断降级、限流控制等保护的方法。通过在方法上添加该注解，可以将该方法纳入Sentinel的保护范围内，从而实现对该方法的保护
     * @return
     */
    @RequestMapping("/world")
    @SentinelResource(value = "helloWorld", blockHandler = "helloWorld1BlockHandler")
    public String helloWorld() {
        return "hello World";
    }

    /**
     * 如果没有全局异常处理类，被流控调用的异常处理方法
     *
     * @return
     */
    public String helloWorld1BlockHandler(BlockException blockException) {
        blockException.printStackTrace();
        return "helloWorld1 被流控";
    }

    /**
     * 关联流控
     * @return
     */
    @RequestMapping("/add")
    @SentinelResource("add")
    public String add() {
        System.out.println("下单成功");
        return "下单成功";
    }

    /**
     * 关联流控
     * @return
     */
    @RequestMapping("/get")
    @SentinelResource("get")
    public String get() {
        System.out.println("查询订单");
        return "查询订单";
    }

    /**
     * 链路流控
     * @return
     */
    @RequestMapping("/test1")
    public String test1() {
        return uservice.getUser();
    }

    /**
     * 链路流控
     * @return
     */
    @RequestMapping("/test2")
    public String test2() {
        return uservice.getUser();

    }

    /**
     * 慢调用比例，熔断降级
     */
    @RequestMapping("/slowThread")
    public String slowThread() throws InterruptedException {
        TimeUnit.SECONDS.sleep(2);
        System.out.println("正常访问");
        return "正常访问";
    }

    /**
     * 异常比例，熔断降级
     */
    @RequestMapping("/err")
    public String err() {
        int i = 1/0;
        return "err";
    }

    /**
     * 访问热点数据异常，必须使用@SentinelResource
     * @param id
     * @return
     */
    @RequestMapping("/get/{id}")
    @SentinelResource(value = "getById",blockHandler = "HotBlockHandler",entryType = EntryType.OUT)
    public String getById(@PathVariable("id") Integer id){
        System.out.println("正常访问");
        return "正常访问";
    }

    public String HotBlockHandler(BlockException e){
        return "访问热点数据异常";
    }

    // 对应的 `handleException` 函数需要位于 `ExceptionUtil` 类中，并且必须为 static 函数.
    @SentinelResource(value = "test", blockHandler = "handleException", blockHandlerClass = {ExceptionUtil.class})
    public void test() {
        System.out.println("Test");
    }

    // 原函数
    @SentinelResource(value = "hello", blockHandler = "exceptionHandler", fallback = "helloFallback")
    public String hello(long s) {
        return String.format("Hello at %d", s);
    }

    // Fallback 函数，函数签名与原函数一致或加一个 Throwable 类型的参数.
    public String helloFallback(long s) {
        return String.format("Halooooo %d", s);
    }

    // Block 异常处理函数，参数最后多一个 BlockException，其余与原函数一致.
    public String exceptionHandler(long s, BlockException ex) {
        // Do some log here.
        ex.printStackTrace();
        return "Oops, error occurred at " + s;

    }


}