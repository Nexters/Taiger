package com.nexters.taiger;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

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
