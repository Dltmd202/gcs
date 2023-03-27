package com.gcs.monolith.domain.context.repository;

import com.gcs.domain.context.AgentContext;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class AgentContextHolder {
    private AgentContext currentAgentContext;

    public void saveContext(AgentContext agentContext){
        currentAgentContext = agentContext;
    }

    public void cleanContext(){
        this.currentAgentContext = null;
    }

    public Optional<AgentContext> getRunningContext(){
        if (Objects.isNull(currentAgentContext)) {
            return Optional.empty();
        }
        return Optional.ofNullable(currentAgentContext);
    }

}
