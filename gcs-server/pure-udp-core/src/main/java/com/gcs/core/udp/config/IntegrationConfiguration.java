package com.gcs.core.udp.config;

import com.gcs.core.udp.domain.mavlink.service.MavlinkGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;


@Configuration
@RequiredArgsConstructor
public class IntegrationConfiguration {

    private final MavlinkGatewayService mavlinkGatewayService;

    @Bean
    public TaskExecutor AgentToBrowserExecutor(){
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(100);
        executor.setMaxPoolSize(100);
        executor.setThreadNamePrefix("AgentToBr");
        executor.initialize();

        return executor;
    }

    @Bean
    public UdpInbound udpInbound(){
        return new UdpInbound(mavlinkGatewayService, AgentToBrowserExecutor());
    }
}
