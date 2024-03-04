package com.example.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements Uservice {


    @Override
    @SentinelResource(value = "getUser",blockHandler = "blockHandlerGetUser")
    public String getUser() {
        return "正常用户";
    }

    public String blockHandlerGetUser(BlockException e) {
        return "流控用户";
    }
}
