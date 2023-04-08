package com.gcs.api.web.agent.api;

import com.gcs.api.domain.mavlink.service.MavLinkService;
import com.gcs.supporter.util.api.ApiUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/agent")
public class AgentApiController {

    private final MavLinkService mavLinkService;

    @PostMapping("/{sysid}/arm")
    public ResponseEntity<Void> arm(
            @PathVariable int sysid
    ){
        mavLinkService.arm(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/disarm")
    public ResponseEntity<Void> disArm(
            @PathVariable int sysid
    ){
        mavLinkService.disarm(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/land")
    public ResponseEntity<Void> land(
            @PathVariable int sysid
    ){
        mavLinkService.land(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/offboard")
    public ResponseEntity<Void> offboard(
            @PathVariable int sysid
    ){
        mavLinkService.offboard(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/takeoff")
    public ResponseEntity<Void> takeOff(
            @PathVariable int sysid,
            @RequestParam(name = "alt") Float alt
    ){
        mavLinkService.takeOff(sysid, alt);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/reboot")
    public ResponseEntity<Void> takeOff(
            @PathVariable int sysid
    ){
        mavLinkService.reboot(sysid);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/{sysid}/position")
    public ResponseEntity<Void> position(
            @PathVariable int sysid,
            @RequestParam(name = "x") Float x,
            @RequestParam(name = "y") Float y,
            @RequestParam(name = "z") Float z
    ){
        mavLinkService.setPosition(sysid, x, y, z);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/arm")
    public ResponseEntity<ApiUtil.ApiResult<Boolean>> arm(){
        mavLinkService.arm();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/disarm")
    public ResponseEntity<Void> disArm(){
        mavLinkService.disarm();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/takeoff")
    public ResponseEntity<Void> takeOff(
            @RequestParam(name = "alt") Float alt
    ){
        mavLinkService.takeOff(alt);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/land")
    public ResponseEntity<Void> land(){
        mavLinkService.land();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/offboard")
    public ResponseEntity<Void> offboard(){
        mavLinkService.offboard();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/destination")
    public ResponseEntity<Void> destination(
            @RequestParam(name = "x") Float x,
            @RequestParam(name = "y") Float y,
            @RequestParam(name = "z") Float z
    ){
        mavLinkService.setDestination(x, y, z);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/reboot")
    public ResponseEntity<Void> reboot(){
        mavLinkService.reboot();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/led")
    public ResponseEntity<Void> led(
            @RequestParam int type,
            @RequestParam int r,
            @RequestParam int g,
            @RequestParam int b,
            @RequestParam int brightness,
            @RequestParam int speed
    ){
        mavLinkService.ledControl(type, r, g, b, brightness, speed);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


}
