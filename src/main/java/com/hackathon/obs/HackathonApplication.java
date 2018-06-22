package com.hackathon.obs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@ComponentScan(basePackages = { "com.hackathon.persist.config", "com.hackathon.obs" })
@EntityScan(basePackages = { "com.hackathon.obs.entidades" })
@EnableJpaRepositories(basePackages = { "com.hackathon.obs.Dao" })
@SpringBootApplication
public class HackathonApplication {

	public static void main(String[] args) {
		SpringApplication.run(HackathonApplication.class, args);
	}
}
