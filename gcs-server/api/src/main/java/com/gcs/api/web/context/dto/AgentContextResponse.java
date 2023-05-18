package com.gcs.api.web.context.dto;

import com.gcs.domain.context.AgentContext;
import com.gcs.domain.context.constants.AgentContextConnection;
import com.gcs.api.web.agent.dto.AgentResponse;
import lombok.Getter;
import lombok.Setter;

import java.util.Map;
import java.util.Objects;
import java.util.function.Function;
import java.util.stream.Collectors;

@Getter @Setter
public class AgentContextResponse {
    private String connection;
    private Map<Integer, AgentResponse> agents;
    private Float rotation;

    public AgentContextResponse(AgentContext agentContext){
        this.agents = agentContext.stream()
                .map(AgentResponse::new)
                .collect(Collectors.toMap(AgentResponse::getSysid, Function.identity()));
        if(Objects.isNull(agentContext.getConnection())){
            this.connection = AgentContextConnection.ROS.getVar();
        } else {
            this.connection = agentContext.getConnection().getVar();
        }
        this.rotation = agentContext.getRotation();
    }
}
