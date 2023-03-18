package com.gcs.monolith.domain.agent.service;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;
import com.gcs.monolith.domain.agent.repository.InMemoryAgentRepository;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final InMemoryAgentRepository agentRepository;

    @Override
    public String getAgentHostAddress(int sysid){
        AgentDto agent = agentRepository.findBySysId(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
        return agent.getIp();
    }

    @Override
    public Integer getAgentPort(int sysid){
        AgentDto agent = agentRepository.findBySysId(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
        return agent.getPort();
    }

    @Override
    public InetAddress getAgentInetAddress(int sysid){
        AgentDto agent = agentRepository.findBySysId(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
        String ip = agent.getIp();
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLlhCoordinate(int sysid, LlhLocatable coordinate){
        Agent agent = getAgentBySisid(sysid);
        agent.update(coordinate);
    }

    @Override
    public void updateNedCoordinate(int sysid, NedLocatable coordinate){
        Agent agent = getAgentBySisid(sysid);
        agent.update(coordinate);
    }

    @Override
    public void updateFlyStatus(int sysid, Flyable fly){
        Agent agent = getAgentBySisid(sysid);
        agent.update(fly);
    }

    @Override
    public void updateMove(int sysid, Moveable move){
        Agent agent = getAgentBySisid(sysid);
        agent.update(move);
    }

    @Override
    public void updateAngle(int sysid, Rotationable angle){
        Agent agent = getAgentBySisid(sysid);
        agent.update(angle);
    }

    @Override
    public void updateVelocity(int sysid, Velociterable velocity){
        Agent agent = getAgentBySisid(sysid);
        agent.update(velocity);
    }

    private Agent getAgentBySisid(int sysid){
        return agentRepository.findBySysId(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
    }


}
