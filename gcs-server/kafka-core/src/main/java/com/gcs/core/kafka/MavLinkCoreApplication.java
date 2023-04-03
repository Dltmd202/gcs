package com.gcs.core.kafka;

import com.gcs.core.kafka.config.AgentConfiguration;
import com.gcs.core.kafka.domain.agent.AgentConfigurationByServiceImpl;
import com.gcs.domain.agent.service.AgentConfigureService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MavLinkCoreApplication {
    public static void main(String[] args) {
        SpringApplication.run(MavLinkCoreApplication.class, args);
    }

    @Bean
    public AgentConfigureService agentService(AgentConfiguration agentConfiguration){
        return new AgentConfigurationByServiceImpl(agentConfiguration);
    }
}