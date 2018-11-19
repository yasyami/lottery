package com.meyasy.sso;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;

@SpringBootApplication
public class LotterySsoApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(LotterySsoApplication.class).run(args);
	}
}
