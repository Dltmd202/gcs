package com.gcs.core.udp.config;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.integration.channel.QueueChannel;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.test.context.ActiveProfiles;

import java.net.DatagramSocket;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("dev")
class IntegrationConfigurationTest {

    @Autowired QueueChannel agentToServerMavlinkChannel;
    @Autowired IntegrationFlow processAgentToServerMavlink;

    @Test
    @DisplayName("에이전트 -> GCS 패킷 채널 Bean 테스트")
    void channalBeanTest(){
        assertThat(agentToServerMavlinkChannel).isNotNull();
        assertThat(agentToServerMavlinkChannel).isInstanceOf(QueueChannel.class);
    }
    @Test
    @DisplayName("UDP -> agentToServerMavlinkChannel IntegrationFlow Bean 테스트")
    void integrationFlowBeanTest(){
        assertThat(processAgentToServerMavlink).isNotNull();
        assertThat(processAgentToServerMavlink).isInstanceOf(IntegrationFlow.class);
        assertThat(processAgentToServerMavlink).isInstanceOf(IntegrationFlow.class);

    }

}