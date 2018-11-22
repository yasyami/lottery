package com.meyasy.pay;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LotteryPayApplication {

    public static void main(String[] args) {
        new SpringApplicationBuilder(LotteryPayApplication.class).web(WebApplicationType.NONE).run(args);
    }
}
