package com.inoriii.hello.spring;

import com.inoriii.hello.spring.resource.config.DynamicDataSourceConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Import;

@Import({DynamicDataSourceConfig.class})
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
public class HelloSpringWebApplication {

    public static void main(String[] args) {
        SpringApplication.run(HelloSpringWebApplication.class, args);
    }

}
