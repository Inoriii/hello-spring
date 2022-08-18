package com.inoriii.hello.spring.api.enums;

/**
 * @author sakura
 * @date: 2021/7/31 21:56
 * @description:
 */
public enum ResponseCode {
    /**
     * SUCCESS,FAIL
     */
    SUCCESS(0, "成功"), FAIL(-1, "系统异常"),
    LOGOUT(-2, "用户已退出"),
    JWT_FAIL(-101, "JWT异常"),
    JWT_VALID_FAIL(-102, "JWT签名校验异常"), JWT_TIMEOUT_FAIL(-103, "JWT认证过期");

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
