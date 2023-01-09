package com.shopSpring.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication//(exclude =  {DataSourceAutoConfiguration.class })//, scanBasePackages= {"com.shopSpring.demo"})
//@ComponentScan({"com.shopSpring.demo"})
//@EnableJpaRepositories("com.shopSpring.demo.repositories")
//@EntityScan("com.shopSpring.demo")
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

}
