package com.gcs.domain.context.model;

import com.gcs.domain.agent.model.Agent;
import com.gcs.domain.context.constants.AgentContextConnection;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

public class AgentContext {
    private AgentContextConnection connection;
    private final Map<Integer, Agent> agents;
    private Float rotation;
    private boolean repeatedHeartbeat = true;
    private boolean repeatedSetPoint = false;

    public AgentContext() {
        agents = new HashMap<>();
    }

    public AgentContext(AgentContextConnection connection, Map<Integer, Agent> agents) {
        this.connection = connection;
        this.agents = agents;
    }

    public AgentContextConnection getConnection() {
        return connection;
    }

    public void setConnection(AgentContextConnection connection) {
        this.connection = connection;
    }

    public void setRotation(float rotation){
        this.rotation = rotation;
    }

    public Float getRotation(){
        return rotation;
    }

    public Map<Integer, Agent> agentStore() {
        return agents;
    }

    public Optional<Agent> getAgent(int sysid) {
        return Optional.ofNullable(agentStore().get(sysid));
    }

    public Stream<Agent> stream(){
        return agents.values().stream();
    }

    public boolean toggleRepeatedHeartbeat(){
        this.repeatedHeartbeat = !this.repeatedHeartbeat;
        return repeatedHeartbeat;
    }

    public boolean toggleRepeatedSetPoint(){
        this.repeatedSetPoint = !this.repeatedSetPoint;
        return this.repeatedSetPoint;
    }

    public boolean isRepeatedHeartbeat() {
        return repeatedHeartbeat;
    }

    public boolean isRepeatedSetPoint() {
        return repeatedSetPoint;
    }
}
