package com.meyasy.user.configurations;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("com.meyasy.user.dao.persistence")
public class MapperScannerConfiguration {
}
