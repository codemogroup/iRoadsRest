package com.codemo.iroads;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class IroadsApplication {

	public static void main(String[] args) {
		SpringApplication.run(IroadsApplication.class, args);
	}
}
