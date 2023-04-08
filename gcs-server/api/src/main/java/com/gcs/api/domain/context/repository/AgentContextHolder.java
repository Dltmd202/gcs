package com.gcs.api.domain.context.repository;

import com.gcs.domain.context.AgentContext;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class AgentContextHolder {
    private AgentContext currentAgentContext;
    private boolean repeatedHeartbeat;
    private boolean repeatedSetPoint;

    public void saveContext(AgentContext agentContext){
        currentAgentContext = agentContext;
        repeatedHeartbeat = true;
        repeatedSetPoint = true;
    }

    public boolean toggleRepeatedHeartbeat(){
        this.repeatedSetPoint = !this.repeatedHeartbeat;
        return this.repeatedHeartbeat;
    }

    public boolean toggleRepeatedSetPoint(){
        this.repeatedSetPoint = !this.repeatedHeartbeat;
        return this.repeatedSetPoint;
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

    public boolean isRepeatedHeartbeat() {
        return repeatedHeartbeat;
    }

    public boolean isRepeatedSetPoint() {
        return repeatedSetPoint;
    }
}
