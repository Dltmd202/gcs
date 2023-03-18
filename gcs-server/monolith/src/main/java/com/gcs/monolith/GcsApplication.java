package com.gcs.monolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@ConfigurationPropertiesScan
@EntityScan(basePackages = {
		"com.gcs.supporter"
})
@EnableJpaRepositories("com.gcs.**")
@SpringBootApplication(scanBasePackages = "com.gcs.monolith")
public class GcsApplication {

	public static void main(String[] args) {
		SpringApplication.run(GcsApplication.class, args);
	}

}
