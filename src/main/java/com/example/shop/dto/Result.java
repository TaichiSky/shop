package com.example.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class Result<T> {
    private int code;
    private String message;
    private T data;

    public Result(T data) {
        this(1, "query success", data);
    }
    public Result(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public Result() {
    }
}
