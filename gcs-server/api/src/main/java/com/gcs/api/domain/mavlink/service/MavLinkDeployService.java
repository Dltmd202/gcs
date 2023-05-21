package com.gcs.api.domain.mavlink.service;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.api.domain.context.service.AgentContextService;
import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.error.exception.GcsException;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
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
public class MavLinkDeployService {
    private final MavLinkService mavLinkService;

    public void takeOff(int sysid, float x, float y, float z, float yaw){
        log.info("fuck you!!!");
        mavLinkService.setDestination(sysid, x, y, z, yaw);
        sleep(1000L);
        mavLinkService.offboard(sysid);
        sleep(1000L);
        mavLinkService.arm(sysid);
    }

    public void move(int sysid, float x, float y, float z, float yaw){
        mavLinkService.setDestination(sysid, x, y, z, yaw);
    }

    public void land(int sysid){
        mavLinkService.land(sysid);
        sleep(1000);
        mavLinkService.disarm(sysid);
        sleep(1000L);
        mavLinkService.reboot(sysid);
        mavLinkService.setDestination(sysid, 0, 0, 0, 0);
    }

    public void reboot(int sysid){
        mavLinkService.reboot(sysid);
    }


    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            throw new ApiException(ErrorCode.SESSION_REMOVE_ERROR);
        }
    }



}
