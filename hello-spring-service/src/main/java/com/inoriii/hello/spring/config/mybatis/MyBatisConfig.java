package com.inoriii.hello.spring.config.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @date: 2022/6/9 23:10
 * @description:
 */
@MapperScan(basePackages = {"com.inoriii.hello.spring.dao.mapper"})
@Component
public class MyBatisConfig {
}
