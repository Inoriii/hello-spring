package com.inoriii.hello.spring.model.constant.enums;

/**
 * @author sakura
 * @date: 2021/7/31 21:56
 * @description:
 */
public enum ResponseCode {
    /**
     * SUCCESS,FAIL
     */
    SUCCESS(0, "成功"),
    FAIL(-1, "系统异常");

    private Integer code;
    private String msg;

    ResponseCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
