package com.gcs.core.mavlink.domain.agent;

import com.gcs.core.mavlink.config.AgentConfiguration;
import com.gcs.domain.agent.service.AgentConfigureService;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.errors.ApiException;

import java.net.InetAddress;
import java.net.UnknownHostException;

@RequiredArgsConstructor
public class AgentConfigurationByServiceImpl implements AgentConfigureService {
    private final AgentConfiguration agentConfiguration;

    public String getAgentHostAddress(int key){
        if(!agentConfiguration.getAgents().containsKey(key))
            throw new ApiException();
        return agentConfiguration.getAgents().get(key).ip;
    }

    public Integer getAgentPort(int key){
        if(!agentConfiguration.getAgents().containsKey(key))
            throw new ApiException();
        return agentConfiguration.getAgents().get(key).port;
    }


    public AgentConfiguration.Address getAgentAddress(int key){
        if(!agentConfiguration.getAgents().containsKey(key))
            throw new ApiException();
        return agentConfiguration.getAgents().get(key);
    }

    public InetAddress getAgentInetAddress(int key){
        if(!agentConfiguration.getAgents().containsKey(key))
            throw new ApiException();
        String ip = agentConfiguration.getAgents().get(key).ip;
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new ApiException();
        }
    }



}
