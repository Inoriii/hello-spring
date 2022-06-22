package com.inoriii.hello.spring.resource.config;

import com.alibaba.druid.spring.boot.autoconfigure.DruidDataSourceBuilder;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"com.inoriii.hello.spring.dao.slave_mapper"}, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataSourceConfig {

    @Bean(name = "slaveDataSource")
    @ConfigurationProperties(prefix = "spring.datasource.druid.slave")
    public DataSource dataSource() {
        return DruidDataSourceBuilder.create().build();
    }

    @Bean(name = "slaveSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("slaveDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/slave_mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = "slaveTransactionManager")
    public DataSourceTransactionManager transactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "slaveSqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier("slaveSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
}