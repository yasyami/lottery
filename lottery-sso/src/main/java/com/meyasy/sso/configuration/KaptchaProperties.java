package com.meyasy.sso.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Properties;

@ConfigurationProperties(prefix = "spring.kaptcha")
public class KaptchaProperties {

    private  Properties properties = new Properties();

    public  Properties getProperties() {
        return properties;
    }

    public  void setProperties(Properties properties) {
        this.properties = properties;
    }
}
