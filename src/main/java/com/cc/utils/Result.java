package com.cc.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Result {
    private int code;
    private Object data;
    private String msg;

    public Result(int code) {
        this.code = code;
    }

    public Result(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Result(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static Result success() {
        return new Result(200, null, "操作成功");
    }

    public static Result success(Object data) {
        return new Result(200, data, "操作成功");
    }

    public static Result success(String msg, Object data) {
        return new Result(200, data, msg);
    }

    public static Result fail() {
        return new Result(401, null, "操作失败");
    }

    public static Result fail(String msg) {
        return new Result(400, null, msg);
    }

    public static Result fail(int code, String msg) {
        return new Result(code, null, msg);
    }
}
