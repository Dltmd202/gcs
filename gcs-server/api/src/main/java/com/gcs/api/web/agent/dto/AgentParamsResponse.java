package com.gcs.api.web.agent.dto;

import com.gcs.domain.agent.model.Agent;
import lombok.Getter;

import java.util.List;

@Getter
public class AgentParamsResponse {
    private Integer sysid;
    List<AgentParamResponse> params;

    public AgentParamsResponse(Agent agent){
        this.sysid = agent.getSysid();
        params = agent.getParameters().values().stream()
                .map(AgentParamResponse::new)
                .toList();
    }

    public AgentParamsResponse(Integer sysid, List<AgentParamResponse> params) {
        this.sysid = sysid;
        this.params = params;
    }
}
