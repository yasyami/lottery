package com.meyasy.configurations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.meyasy.user.persistence.dao")
public class MapperScannerConfiguration {
}
