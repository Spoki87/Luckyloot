package com.luckyloot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class LuckylootApplication {

	public static void main(String[] args) {
		SpringApplication.run(LuckylootApplication.class, args);
	}

}
