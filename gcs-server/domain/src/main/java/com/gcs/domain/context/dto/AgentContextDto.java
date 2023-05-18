package com.gcs.domain.context.dto;

import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.constants.AgentContextConnection;

import java.util.HashMap;
import java.util.Map;

public class AgentContextDto implements AgentContext {
    private AgentContextConnection connection;
    private final Map<Integer, AgentDto> agents;
    private Float rotation;

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

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public Float getRotation(){
        return rotation;
    }

    @Override
    public Map<Integer, AgentDto> agentStore() {
        return agents;
    }
}
