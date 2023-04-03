package com.gcs.api.domain.mavlink.service;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.api.domain.context.service.AgentContextService;
import com.gcs.api.domain.mavlink.factory.MavLinkMessageFactory;
import com.gcs.domain.mavlink.anntation.MavLinkOrder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class MavLinkService {
    private final AgentContextService contextService;

    @MavLinkOrder
    public MAVLinkMessage arm(int sysid){
        return MavLinkMessageFactory.armMessage(sysid);
    }

    @MavLinkOrder
    public MAVLinkMessage disarm(int sysid){
        return MavLinkMessageFactory.disarmMessage(sysid);
    }

    @MavLinkOrder
    public MAVLinkMessage offboard(int sysid){
        return MavLinkMessageFactory.offBoardMessage(sysid);
    }

    @MavLinkOrder
    public MAVLinkMessage land(int sysid){
        return MavLinkMessageFactory.landMessage(sysid);
    }

    @MavLinkOrder
    public MAVLinkMessage takeOff(int sysid, float alt){
        return MavLinkMessageFactory.takeOffMessage(sysid, alt);
    }

    @MavLinkOrder
    public MAVLinkMessage setPosition(int sysid, float x, float y, float z){
        return MavLinkMessageFactory.setPoint(sysid, x, y, z);
    }

    @MavLinkOrder
    public MAVLinkMessage reboot(int sysid){
        return MavLinkMessageFactory.rebootMessage(sysid);
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> arm(){
        return contextService.getRunningContext().stream()
                .map(MavLinkMessageFactory::armMessage)
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> disarm(){
        return contextService.getRunningContext().stream()
                .map(MavLinkMessageFactory::disarmMessage)
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> offboard(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.offBoardMessage(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> land(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.landMessage(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> takeOff(Float alt){
        List<MAVLinkMessage> messages = new ArrayList<>();
        for (AgentDto agent : contextService.getRunningContext().agents()) {
            messages.add(MavLinkMessageFactory.armMessage(agent.getSysid()));
            messages.add(MavLinkMessageFactory.takeOffMessage(agent.getSysid(), alt));
        }
        return messages;
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> setPosition(float x, float y, float z){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.setPoint(a.getSysid(), x, y, z))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> reboot(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.rebootMessage(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    @Scheduled(fixedRate = 1000)
    public Collection<MAVLinkMessage> scheduledHeartbeat(){
        log.debug("hearbeat");
        if(contextService.isRunningContext())
            return heartBeat();
        return null;
    }

    @MavLinkOrder
    @Scheduled(fixedRate = 500)
    public Collection<MAVLinkMessage> scheduledSetPoint(){
        if(contextService.isRunningContext())
            return setPoint();
        return null;
    }

    private Collection<MAVLinkMessage> heartBeat(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.hearBeat(a.getSysid()))
                .collect(Collectors.toList());
    }

    private Collection<MAVLinkMessage> setPoint(){
        return contextService.getRunningContext().stream()
                .map(MavLinkMessageFactory::setPoint)
                .collect(Collectors.toList());
    }
}
