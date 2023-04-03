package com.gcs.core.udp.config;

import com.gcs.core.udp.domain.mavlink.service.CoreMavlinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;


@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final CoreMavlinkService mavlinkService;
    @Bean
    QueueChannel agentToServerMavlinkChannel(){
        return new QueueChannel();
    }

    @Bean
    QueueChannel serverToAgentMavlinkChannel(){
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow processAgentToServerMavlink(){
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(9750))
                .handle(mavlinkService, "publishToBrowser")
                .channel("agentToServerMavlinkChannel")
                .get();
    }
}
