package com.inoriii.hello.spring.api.vo;

import com.inoriii.hello.spring.api.enums.ResponseCode;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RestResult<T> {

    /**
     * uuid,用作唯一标识符，供序列化和反序列化时检测是否一致
     */
    private static final long serialVersionUID = 7498483649536881777L;
    /**
     * 标识代码，0表示成功，非0表示出错
     */
    private Integer code;

    /**
     * 提示信息，通常供报错时使用
     */
    private String msg;

    /**
     * 正常返回时返回的数据
     */
    private T data;

    public RestResult() {
        this(ResponseCode.SUCCESS, null);
    }

    public RestResult(T data) {
        this(ResponseCode.SUCCESS, data);
    }

    public RestResult(ResponseCode responseCode, T data) {
        this(ResponseCode.SUCCESS.getCode(), ResponseCode.SUCCESS.getMsg(), data);
    }

    public RestResult(Integer code, String msg) {
        this(code, msg, null);
    }

    public RestResult(ResponseCode responseCode) {
        this(responseCode.getCode(), responseCode.getMsg(), null);
    }
}
