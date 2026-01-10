package com.at.t.eCommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.at.t.eCommerce.config.JWTProperties;


@SpringBootApplication
@EnableConfigurationProperties(JWTProperties.class)
@EntityScan("com.at.t.eCommerce")
@EnableJpaRepositories("com.at.t.eCommerce")
public class ECommerceApplication {

	public static void main(String[] args) {
		SpringApplication.run(ECommerceApplication.class, args);
	}

}
