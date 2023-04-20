package com.gcs.api.domain.mavlink.service;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.api.domain.context.service.AgentContextService;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.supporter.mavlink.annotation.MavLinkOrder;
import com.gcs.supporter.mavlink.message.factory.MavLinkMessageFactory;
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
            messages.add(MavLinkMessageFactory.takeOffMessage(agent.getSysid(), alt));
        }
        return messages;
    }

    public void setDestination(float x, float y, float z){
        contextService.getRunningContext().stream()
                .forEach(a -> a.setDestination(new NedCoordinate(x, y, z)));
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
    @Scheduled(fixedRate = 400)
    public Collection<MAVLinkMessage> scheduledSetPoint(){
        if(contextService.isRepeatedSetPoint() && contextService.isRunningContext())
            return setPoint();
        return null;
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> setScenarioStartTime(float startTime){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.setScenarioStartTime(a.getSysid(), startTime))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> stopScenario(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.stopScenario(a.getSysid()))
                .collect(Collectors.toList());
    }


    @MavLinkOrder
    public Collection<MAVLinkMessage> resetScenario(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.resetScenario(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> setScenarioConfigs(float offsetX, float offsetY, float rotation, String filepath){
        // TODO filter Agent Status Fixed Bit Mask

        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.setScenarioConfigs(
                        a.getSysid(),
                        offsetX,
                        offsetY,
                        rotation,
                        filepath
                ))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public MAVLinkMessage setScenarioConfigs(int sysid, float offsetX, float offsetY, float rotation, String filepath){
        return MavLinkMessageFactory.setScenarioConfigs(
                sysid,
                offsetX,
                offsetY,
                rotation,
                filepath
        );
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> emergencyLanding(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.emergencyLanding(a.getSysid()))
                .collect(Collectors.toList());
    }


    //TODO 파라미터 패킹
    @MavLinkOrder
    public Collection<MAVLinkMessage> ledControl(int type, int r, int g, int b, int brightness, int speed){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.ledCommand(
                        a.getSysid(), type, r, g, b, brightness, speed)
                )
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> calibrationGyro(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.calibrationGyro(a.getSysid()))
                .collect(Collectors.toList());
    }

    @MavLinkOrder
    public Collection<MAVLinkMessage> calibrationLevel(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.calibrationLevel(a.getSysid()))
                .collect(Collectors.toList());
    }


    @MavLinkOrder
    public Collection<MAVLinkMessage> calibrationAccel(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.calibrationAccel(a.getSysid()))
                .collect(Collectors.toList());
    }





    private Collection<MAVLinkMessage> heartBeat(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.heartBeat(a.getSysid()))
                .collect(Collectors.toList());
    }

    private Collection<MAVLinkMessage> setPoint(){
        return contextService.getRunningContext().stream()
                .map(a -> MavLinkMessageFactory.setPoint(a, a.getDestination()))
                .collect(Collectors.toList());
    }
}
