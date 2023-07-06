package com.gcs.api.domain.agent.service;

import com.gcs.api.domain.context.repository.AgentContextHolder;
import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.agent.model.Agent;
import com.gcs.domain.agent.model.Parameter;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgentServiceImpl implements AgentService {
    private final AgentContextHolder agentContextHolder;

    @Override
    public String getAgentHostAddress(int sysid){
        Agent agent = getAgentBySystemId(sysid);
        return agent.getIp();
    }

    @Override
    public Integer getAgentPort(int sysid){
        Agent agent = getAgentBySystemId(sysid);
        return agent.getPort();
    }

    @Override
    public InetAddress getAgentInetAddress(int sysid){
        Agent agent = getAgentBySystemId(sysid);
        String ip = agent.getIp();
        try {
            return InetAddress.getByName(ip);
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateLlhCoordinate(int sysid, LlhLocatable coordinate){
        getAgentBySystemId(sysid).update(coordinate);
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
        getAgentBySystemId(sysid).update(move);
    }

    @Override
    public void updateAngle(int sysid, Rotationable angle){
        getAgentBySystemId(sysid).update(angle);
    }

    @Override
    public void updateVelocity(int sysid, Velociterable velocity){
        getAgentBySystemId(sysid).update(velocity);
    }

    @Override
    public void updateParameter(int sysid, Parameter parameter) {
        getAgentBySystemId(sysid).update(parameter);
    }

    @Override
    public List<Parameter> readParameter(int sysid){
        return getAgentBySystemId(sysid).getParameters().values().stream().toList();
    }

    private Agent getAgentBySystemId(int sysid) {
        return agentContextHolder.getAgent(sysid);
    }

    @Override
    public void updateRtkCoordinate(int sysid, NedLocatable coordinate) {

    }
}
