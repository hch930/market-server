package com.market.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class MarketServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MarketServerApplication.class, args);
	}

}
