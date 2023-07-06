package com.gcs.api.web.agent.dto;

import com.gcs.domain.agent.model.Parameter;

public class AgentParamResponse {
    String paramId;
    Object value;

    public AgentParamResponse(Parameter parameter){
        this.paramId = parameter.getParamId();
        this.value = parameter.getValue();
    }
}
