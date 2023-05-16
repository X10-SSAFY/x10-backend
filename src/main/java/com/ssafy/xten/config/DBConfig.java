package com.ssafy.xten.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan(basePackages = "com.ssafy.xten.model.dao")
public class DBConfig {
}
