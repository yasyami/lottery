package com.meyasy.sso.configuration;

import com.google.code.kaptcha.Producer;
import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@EnableConfigurationProperties(KaptchaProperties.class)
public class KaptchaConfiguration {

    @Bean
    public Producer getProducer(KaptchaProperties properties){
        DefaultKaptcha kaptcha = new DefaultKaptcha();
        Config config = new Config(properties.getProperties());
        kaptcha.setConfig(config);
        return kaptcha;
    }


}
