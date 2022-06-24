package com.inoriii.hello.spring.web.converter;


import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.support.config.FastJsonConfig;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;
import org.springframework.boot.autoconfigure.http.HttpMessageConverters;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * @author sakura
 * @date: 2021/8/2 21:12
 * @description:
 */
@Component
public class HttpMessageConverter {
    /**
     * 配置FastJson
     *
     * @return HttpMessageConverters
     */
    @Bean
    public HttpMessageConverters fastJsonHttpMessageConverters() {
        // 定义一个converters转换消息的对象
        FastJsonHttpMessageConverter fastConverter = new FastJsonHttpMessageConverter();
        // 添加fastjson的配置信息，比如: 是否需要格式化返回的json数据
        FastJsonConfig fastJsonConfig = new FastJsonConfig();
        fastJsonConfig.setSerializerFeatures(SerializerFeature.PrettyFormat);
        fastJsonConfig.setDateFormat("yyyy-MM-dd HH:mm:ss");
        // 在converter中添加配置信息
        fastConverter.setFastJsonConfig(fastJsonConfig);
        // 返回HttpMessageConverters对象
        return new HttpMessageConverters(fastConverter);
    }
}
