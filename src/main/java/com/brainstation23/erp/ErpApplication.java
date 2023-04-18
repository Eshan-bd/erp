package com.brainstation23.erp;

import com.brainstation23.erp.exception.custom.GlobalExceptionHandler;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ErpApplication {

	public static void main(String[] args) {

		Thread.setDefaultUncaughtExceptionHandler(new GlobalExceptionHandler());
		SpringApplication.run(ErpApplication.class, args);
	}

}
