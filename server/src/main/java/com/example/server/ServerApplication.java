package com.example.server;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ServerApplication {

        /**
         * @params arg for findbugs
         */
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}
}
