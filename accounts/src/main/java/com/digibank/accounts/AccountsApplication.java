package com.digibank.accounts;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing(auditorAwareRef = "auditAwareImpl")
@OpenAPIDefinition(
		info = @io.swagger.v3.oas.annotations.info.Info(
				title = "Accounts Microservice Rest API Documentation",
				description = "This is a REST API Documentation for Accounts Microservice in DigiBank Application",
				version = "1.0",
				contact = @io.swagger.v3.oas.annotations.info.Contact(
						name = "Tej Bharath Mujje",
						email = "tmujje@mydigibank.com"
				)
		)
)
public class AccountsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AccountsApplication.class, args);
	}

}
