package com.gcs.core.udp.domain.mavlink.service;

import com.gcs.api.domain.context.repository.AgentContextHolder;
import com.gcs.api.domain.mavlink.service.MavLinkService;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.dto.AgentContextDto;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.net.DatagramSocket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import static com.gcs.domain.context.constants.AgentContextConnection.MAV_LINK;


@SpringBootTest
@ActiveProfiles("dev")
class MavlinkGatewayServiceTest {

    private static final String TEST_HOST = "127.0.0.1";
    private static final int TEST_PORT = 9999;

    @Autowired MavlinkGatewayService gatewayService;
    @Autowired AgentContextHolder agentContextHolder;
    @Autowired MavLinkService mavLinkService;
    private DatagramSocket reciever;
    private static AgentContext agentContext;

    @BeforeAll
    static void init(){
        Map<Integer, AgentDto> agents = new HashMap<>();
        agents.put(
                1,
                AgentDto.builder()
                        .ip(TEST_HOST)
                        .port(TEST_PORT)
                        .build());

        agentContext = new AgentContextDto(MAV_LINK, agents);
    }


    @BeforeEach
    void setUp() throws SocketException {
        reciever = new DatagramSocket(TEST_PORT);
        agentContextHolder.cleanContext();
        agentContextHolder.saveContext(agentContext);
    }



}