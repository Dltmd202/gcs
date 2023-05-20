package com.gcs.api.web.agent.api;

import com.gcs.api.domain.mavlink.service.MavLinkDeployService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/deploy")
public class AgentDeployApiController {

    private final MavLinkDeployService mavLinkDeployService;

    @PostMapping("/{sysid}/takeoff")
    public ResponseEntity<Void> takeOff(
            @PathVariable int sysid,
            @RequestParam(name = "x") Float x,
            @RequestParam(name = "y") Float y,
            @RequestParam(name = "z") Float z,
            @RequestParam(name = "yaw") Float yaw
    ){
        mavLinkDeployService.takeOff(sysid, x, y, z, yaw);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/reboot")
    public ResponseEntity<Void> takeOff(
            @PathVariable int sysid
    ){
        mavLinkDeployService.reboot(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/move")
    public ResponseEntity<Void> move(
            @PathVariable int sysid,
            @RequestParam(name = "x") Float x,
            @RequestParam(name = "y") Float y,
            @RequestParam(name = "z") Float z,
            @RequestParam(name = "yaw", required = false) Float yaw
    ){
        mavLinkDeployService.move(sysid, x, y, z, yaw);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/land")
    public ResponseEntity<Void> land(
            @PathVariable int sysid
    ){
        mavLinkDeployService.land(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

}
