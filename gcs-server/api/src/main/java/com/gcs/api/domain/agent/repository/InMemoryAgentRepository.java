package com.gcs.api.domain.agent.repository;

import com.gcs.domain.agent.model.Agent;
import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class InMemoryAgentRepository {
    private final Map<Integer, Agent> agentStore = new ConcurrentHashMap<>();
    public Integer saveAgent(Integer sysid, Agent agent){
        agentStore.put(sysid, agent);
        return sysid;
    }

    public Optional<Agent> findBySysId(Integer sysid){
        return Optional.ofNullable(agentStore.get(sysid));
    }
}
