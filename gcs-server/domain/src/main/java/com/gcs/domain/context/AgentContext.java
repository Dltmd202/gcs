package com.gcs.domain.context;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.context.constants.AgentContextConnection;

import java.util.Map;
import java.util.stream.Stream;

public interface AgentContext {
    AgentContextConnection getConnection();

    Map<Integer, AgentDto> agentStore();

    default Iterable<AgentDto> agents(){
        return agentStore().values();
    }

    default Stream<AgentDto> stream(){
        return agentStore().values().stream();
    }
}