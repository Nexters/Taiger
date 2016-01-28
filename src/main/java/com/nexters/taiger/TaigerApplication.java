package com.nexters.taiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@EnableConfigurationProperties
@EnableAutoConfiguration
@SpringBootApplication
public class TaigerApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaigerApplication.class, args);
	}

	@Bean(initMethod = "init")
	public InitDataLoader initDataLoader(){
		return new InitDataLoader();
	}

}
