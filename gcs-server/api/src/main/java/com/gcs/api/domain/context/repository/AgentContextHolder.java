package com.gcs.api.domain.context.repository;

import com.gcs.domain.agent.model.Agent;
import com.gcs.domain.context.model.AgentContext;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import org.springframework.stereotype.Repository;

import java.util.Objects;
import java.util.Optional;

@Repository
public class AgentContextHolder {
    private AgentContext currentAgentContext;

    public void saveContext(AgentContext agentContext){
        currentAgentContext = agentContext;
    }

    public boolean toggleRepeatedHeartbeat(){
        return currentAgentContext.toggleRepeatedHeartbeat();
    }

    public boolean toggleRepeatedSetPoint(){
        return currentAgentContext.toggleRepeatedSetPoint();
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

    public Agent getAgent(int sysid){
        return currentAgentContext.getAgent(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
    }

    public boolean isRepeatedSetPoint() {
        return currentAgentContext.isRepeatedSetPoint();
    }
}
