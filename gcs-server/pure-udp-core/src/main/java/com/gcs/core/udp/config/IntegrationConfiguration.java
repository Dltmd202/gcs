package com.gcs.core.udp.config;

import com.gcs.core.udp.domain.mavlink.service.MavlinkGatewayService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
@RequiredArgsConstructor
public class IntegrationConfiguration {

    private final MavlinkGatewayService mavlinkGatewayService;

    @Bean
    public UdpInbound udpInbound(){
        return new UdpInbound(mavlinkGatewayService);
    }
}
