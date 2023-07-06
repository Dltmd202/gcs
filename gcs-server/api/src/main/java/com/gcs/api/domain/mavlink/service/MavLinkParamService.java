package com.gcs.api.domain.mavlink.service;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.api.domain.context.service.AgentContextService;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.supporter.mavlink.annotation.MavLinkOrder;
import com.gcs.supporter.mavlink.message.factory.MavLinkMessageFactory;
import com.gcs.supporter.mavlink.param.DroneShow;
import com.gcs.supporter.mavlink.param.ParameterKeyDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MavLinkParamService {

    private final AgentContextService contextService;

    public List<ParameterKeyDto> droneShowParamKeyList(){
        return DroneShow.of();
    }

    @MavLinkOrder
    public MAVLinkMessage paramList(int sysid){
        return MavLinkMessageFactory.parameterList(sysid);
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> droneShowParamList(int sysid){
        return Arrays.stream(DroneShow.values())
                .map(p -> MavLinkMessageFactory.requestParams(sysid, p.getValue()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> paramList(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.parameterList(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public MAVLinkMessage searchParam(int sysid, String key){
        return MavLinkMessageFactory.requestParams(sysid, key);
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> searchParam(String key){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.requestParams(a.getSysid(), key))
                .collect(Collectors.toList());
    }


    @MavLinkOrder
    public MAVLinkMessage setParam(int sysid, String key, Object value){
        return MavLinkMessageFactory.setParam(sysid, key, value);
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> setParam(String key, Object value){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.setParam(a.getSysid(), key, value))
                .collect(Collectors.toList());
    }

}
