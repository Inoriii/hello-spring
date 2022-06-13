package com.inoriii.hello.spring.resource.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @date: 2022/6/9 23:10
 * @description:
 */
@Component
@MapperScan("com.inoriii.hello.spring.dao.mapper")
public class MyBatisConfig {
}
