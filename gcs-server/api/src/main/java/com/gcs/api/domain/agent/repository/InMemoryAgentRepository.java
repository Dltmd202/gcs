package com.gcs.api.domain.agent.repository;

import com.gcs.domain.agent.dto.AgentDto;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAgentRepository {
    private final Map<Integer, AgentDto> agentStore = new ConcurrentHashMap<>();
    public Integer saveAgent(Integer sysid, AgentDto agent){
        agentStore.put(sysid, agent);
        return sysid;
    }

    public Optional<AgentDto> findBySysId(Integer sysid){
        return Optional.ofNullable(agentStore.get(sysid));
    }
}
