package com.gcs.mavlinkmonolith;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = {
		"com.gcs.monolith",
		"com.gcs.core.mavlink"
})
@EntityScan(basePackages = {"com.gcs.supporter.domain"})
@EnableJpaRepositories("com.gcs.**")
@SpringBootApplication(scanBasePackages = {
		"com.gcs.monolith.config",
		"com.gcs.monolith.constants",
		"com.gcs.monolith.domain",
		"com.gcs.monolith.socket",
		"com.gcs.monolith.util",
		"com.gcs.monolith.web",
		"com.gcs.core.mavlink.config",
		"com.gcs.core.mavlink.domain",
})
public class MavlinkMonolithApplication {

	public static void main(String[] args) {
		SpringApplication.run(MavlinkMonolithApplication.class, args);
	}

}
