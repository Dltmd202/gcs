package com.gcs.core.mavlink.config;

import com.gcs.core.mavlink.domain.service.CoreMessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.dsl.Pollers;
import org.springframework.integration.ip.udp.UnicastReceivingChannelAdapter;
import org.springframework.integration.kafka.dsl.Kafka;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.listener.ContainerProperties;

import static com.gcs.core.mavlink.config.CoreConstants.*;

@Configuration
@EnableIntegration
@RequiredArgsConstructor
public class IntegrationConfig {

    private final CoreMessageService mavlinkService;
    private final AgentConfiguration agentConfiguration;
    private final ConsumerFactory<Integer, byte[]> consumerFactory;

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
                .from(new UnicastReceivingChannelAdapter(GCS_PORT))
                .handle(mavlinkService, "publishToServer")
                .channel("agentToServerMavlinkChannel")
                .get();
    }

    @Bean
    public IntegrationFlow processServerToAgentMavlink(){
        return IntegrationFlows
                .from(
                        Kafka.inboundChannelAdapter(
                                consumerFactory,
                                new ContainerProperties(SERVER_TO_AGENT_TOPIC)
                        ),
                        e -> e.poller(Pollers.fixedDelay(KAFKA_POLLING_PERIOD)))
                .handle(mavlinkService, "sendToAgent")
                .get();
    }

}
