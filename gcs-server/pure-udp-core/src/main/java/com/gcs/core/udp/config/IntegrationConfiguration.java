package com.gcs.core.udp.config;

import com.gcs.core.udp.domain.mavlink.service.MavlinkGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;


@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class IntegrationConfiguration {

    private final MavlinkGatewayService mavlinkGatewayService;
    @Bean
    QueueChannel agentToServerMavlinkChannel(){
        return new QueueChannel();
    }

    @Bean
    public IntegrationFlow processAgentToServerMavlink(){
        return IntegrationFlows
                .from(new UnicastReceivingChannelAdapter(9750))
                .handle(mavlinkGatewayService, "publishToBrowser")
                .channel("agentToServerMavlinkChannel")
                .get();
    }
}
