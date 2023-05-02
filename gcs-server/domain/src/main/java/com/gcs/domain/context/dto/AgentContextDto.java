package com.gcs.domain.context.dto;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.constants.AgentContextConnection;
import lombok.val;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

public class AgentContextDto implements AgentContext {
    private AgentContextConnection connection;
    private final Map<Integer, AgentDto> agents;
    private Integer rotation;

    public AgentContextDto() {
        agents = new HashMap<>();
    }

    public AgentContextDto(AgentContextConnection connection, Map<Integer, AgentDto> agents) {
        this.connection = connection;
        this.agents = agents;
    }

    @Override
    public AgentContextConnection getConnection() {
        return connection;
    }

    public void setConnection(AgentContextConnection connection) {
        this.connection = connection;
    }

    public void setRotation(int rotation){
        this.rotation = rotation;
    }

    public int getRotation(){
        return rotation;
    }

    @Override
    public Map<Integer, AgentDto> agentStore() {
        return agents;
    }
}
