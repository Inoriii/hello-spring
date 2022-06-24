package com.inoriii.hello.spring.common.annotation;

import com.inoriii.hello.spring.model.enums.DataSourceName;

import java.lang.annotation.*;

/**
 * @author sakura
 */
@Documented
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DataSource {
    DataSourceName value() default DataSourceName.MASTER;
}