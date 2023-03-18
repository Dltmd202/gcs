package com.gcs.domain.agent.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class AgentTest {
    @Test
    @DisplayName("agentDto 생성 테스트")
    void createAgent(){
        AgentDto agent1 = AgentDto.makeAgent(1, "127.0.0.1", 9750);
        Assertions.assertEquals(agent1.getIp(), "127.0.0.1");
        Assertions.assertEquals(agent1.getPort(), 9750);
        Assertions.assertEquals(agent1.getSysid(), 1);

        AgentDto agent2 = AgentDto.makeAgent(1, "127.0.0.1", 9750);
    }

    @Test
    @DisplayName("agentDto 생성 테스트")
    void createEqualAgent(){
        AgentDto agent1 = AgentDto.makeAgent(1, "127.0.0.1", 9750);
        AgentDto agent2 = AgentDto.makeAgent(1, "127.0.0.1", 9750);
        AgentDto agent3 = AgentDto.makeAgent(1, "127.0.0.1", 9751);
        AgentDto agent4 = AgentDto.makeAgent(2, "127.0.0.1", 9750);

        Assertions.assertEquals(agent1, agent2);
        Assertions.assertEquals(agent1, agent3);
        Assertions.assertNotEquals(agent1, agent4);
    }
}