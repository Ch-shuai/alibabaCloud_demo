package com.example.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseResult<T> {

    private Integer code;

    private String msg;

    private T data;

    public static ResponseResult err(Integer code, String msg) {
        return new ResponseResult(code,msg,null);
    }
}
