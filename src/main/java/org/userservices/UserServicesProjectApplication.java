package org.userservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class UserServicesProjectApplication {

	public static void main(String[] args) {

		SpringApplication.run(UserServicesProjectApplication.class, args);
		System.out.println("meraj");
	}

}
