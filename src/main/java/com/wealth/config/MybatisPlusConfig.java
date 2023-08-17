package com.wealth.config;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@MapperScan("com.wealth.mapper")
@EnableTransactionManagement
@Configuration
public class MybatisPlusConfig {

}
