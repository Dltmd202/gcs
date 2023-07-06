package com.gcs.domain.agent.service;

import com.gcs.domain.agent.model.Parameter;

import java.util.List;

public interface AgentParamService {
    List<Parameter> readParameter(int sysid);
}
