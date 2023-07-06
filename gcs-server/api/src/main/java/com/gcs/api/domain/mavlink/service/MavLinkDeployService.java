package com.gcs.api.domain.mavlink.service;

import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.error.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class MavLinkDeployService {
    private final MavLinkService mavLinkService;

    public void takeOff(int sysid, float x, float y, float z, float yaw){
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
        sleep(500);
        mavLinkService.reboot(sysid);
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
