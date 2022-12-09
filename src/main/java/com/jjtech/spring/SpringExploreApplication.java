package com.jjtech.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class SpringExploreApplication {

	public static void main(String[] args) {
		System.out.println("Start Boot app");
		SpringApplication.run(SpringExploreApplication.class, args);
	}

}
