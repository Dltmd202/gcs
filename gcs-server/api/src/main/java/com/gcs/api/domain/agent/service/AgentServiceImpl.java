package com.gcs.api.domain.agent.service;

import com.gcs.api.domain.context.repository.AgentContextHolder;
import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.AgentConfigureable;
import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentContextHolder agentContextHolder;

    @Override
    public String getAgentHostAddress(int sysid){
        AgentConfigureable agent = getAgentBySystemId(sysid);
        return agent.getIp();
    }

    @Override
    public Integer getAgentPort(int sysid){
        AgentConfigureable agent = getAgentBySystemId(sysid);
        return agent.getPort();
    }

    @Override
    public InetAddress getAgentInetAddress(int sysid){
        AgentConfigureable agent = getAgentBySystemId(sysid);
        String ip = agent.getIp();
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLlhCoordinate(int sysid, LlhLocatable coordinate){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(coordinate);
    }

    @Override
    public void updateNedCoordinate(int sysid, NedLocatable coordinate){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(coordinate);
    }

    @Override
    public void updateFlyStatus(int sysid, Flyable fly){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(fly);
    }

    @Override
    public void updateMove(int sysid, Moveable move){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(move);
    }

    @Override
    public void updateAngle(int sysid, Rotationable angle){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(angle);
    }

    @Override
    public void updateVelocity(int sysid, Velociterable velocity){
        Agent agent = getAgentBySystemId(sysid);
        agent.update(velocity);
    }

    private Agent getAgentBySystemId(int sysid) {
        return agentContextHolder.getRunningContext()
                .orElseThrow(() -> new ApiException(ErrorCode.NO_RUNNING_CONTEXT_CONF))
                .getAgent(sysid)
                .orElseThrow(() -> new ApiException(ErrorCode.NO_AGENT_FROM_CONTEXT_CONF));
    }

    @Override
    public void updateRtkCoordinate(int sysid, NedLocatable coordinate) {

    }
}
