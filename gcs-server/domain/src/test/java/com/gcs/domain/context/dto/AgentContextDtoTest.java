package com.gcs.domain.context.dto;

import com.gcs.domain.context.constants.AgentContextConnection;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Map;


class AgentContextDtoTest {

    @Test
    @DisplayName("AgentContextDto 생성 테스트")
    void createContext(){
        AgentContextDto agentContextDto = new AgentContextDto(
                AgentContextConnection.ROS, Map.of());

        Assertions.assertEquals(
                agentContextDto.getConnection(), AgentContextConnection.ROS
        );

        Assertions.assertEquals(agentContextDto.agentStore().size(), 0);

    }

}