package com.inoriii.hello.spring.common.annotation;

import com.inoriii.hello.spring.model.constant.enums.DataSourceName;

import java.lang.annotation.*;

@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceName value() default DataSourceName.MASTER;
}