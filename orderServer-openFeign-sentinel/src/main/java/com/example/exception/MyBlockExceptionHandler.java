package com.example.exception;

import com.alibaba.csp.sentinel.adapter.spring.webmvc.callback.BlockExceptionHandler;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.authority.AuthorityException;
import com.alibaba.csp.sentinel.slots.block.degrade.DegradeException;
import com.alibaba.csp.sentinel.slots.block.flow.FlowException;
import com.alibaba.csp.sentinel.slots.block.flow.param.ParamFlowException;
import com.alibaba.csp.sentinel.slots.system.SystemBlockException;
import com.alibaba.fastjson.JSON;
import com.example.domain.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
@Slf4j
public class MyBlockExceptionHandler implements BlockExceptionHandler {

    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, BlockException e) throws Exception {

        log.info("BlockExceptionHandler BlockException = :{}", e.getRule());
        ResponseResult rs = null;

        if (e instanceof FlowException)
            rs = ResponseResult.err(-1, "接口限流了");
        else if (e instanceof DegradeException)
            rs = ResponseResult.err(-2, "服务降级了");
        else if (e instanceof ParamFlowException)
            rs = ResponseResult.err(-3, "参数限流了");
        else if (e instanceof AuthorityException)
            rs = ResponseResult.err(-4, "权限规则不通过");
        else if (e instanceof SystemBlockException)
            rs = ResponseResult.err(-5, "系统保护");

        httpServletResponse.setContentType("application/json;charset=utf-8");
        httpServletResponse.getWriter().write(JSON.toJSONString(rs));

    }
}
