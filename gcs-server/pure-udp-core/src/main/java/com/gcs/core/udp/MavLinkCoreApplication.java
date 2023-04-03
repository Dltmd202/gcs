package com.gcs.core.udp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableJpaAuditing
@ConfigurationPropertiesScan(basePackages = {
        "com.gcs.api",
        "com.gcs.core.udp"
})
@EntityScan(basePackages = {"com.gcs.supporter.domain"})
@EnableJpaRepositories("com.gcs.**")
@SpringBootApplication(scanBasePackages = {
        "com.gcs.api.config",
        "com.gcs.api.constants",
        "com.gcs.api.domain",
        "com.gcs.api.realtime",
        "com.gcs.api.util",
        "com.gcs.api.web",
        "com.gcs.core.udp.config",
        "com.gcs.core.udp.domain",
})
public class MavLinkCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MavLinkCoreApplication.class, args);
    }
}
