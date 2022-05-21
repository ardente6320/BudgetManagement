package com.oliver.toy.budgetmanagementapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
@SpringBootApplication
public class BudgetManagementApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetManagementApiApplication.class, args);
	}
}