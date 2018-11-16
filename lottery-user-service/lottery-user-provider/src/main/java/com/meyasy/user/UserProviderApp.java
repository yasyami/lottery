package com.meyasy.user;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

/**
 * Hello world!
 */
@SpringBootApplication
public class UserProviderApp {

    public static void main(String[] args) {
        new SpringApplicationBuilder(UserProviderApp.class).web(WebApplicationType.NONE).run(args);
    }

}
