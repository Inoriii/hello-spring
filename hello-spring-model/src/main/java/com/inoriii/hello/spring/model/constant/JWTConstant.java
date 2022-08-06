package com.inoriii.hello.spring.model.constant;

import java.util.HashMap;
import java.util.Map;

/**
 * @author sakura
 * @date: 2022/8/4 23:20
 * @description:
 */
public interface JWTConstant {
    Map<String, Object> HEADERS = new HashMap<>() {
        {
            put("typ", "JWT");
            put("alg", "HS256");
        }
    };

    byte[] KEY = "hello-spring-jwt".getBytes();

    Long TIMEOUT_MINUTES = 30L;
}
