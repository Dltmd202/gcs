package com.gcs.api.web.agent.api;

import com.gcs.api.domain.mavlink.service.MavLinkService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/scenario")
public class AgentScenarioApiController {
    private final MavLinkService mavLinkService;

    @PostMapping("/{sysid}")
    public ResponseEntity<Void> setConfiguration(
            @PathVariable int sysid,
            @RequestParam float x,
            @RequestParam float y,
            @RequestParam float rot,
            @RequestParam String path
    ){
        mavLinkService.setScenarioConfigs(sysid, x, y, rot, path);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping
    public ResponseEntity<Void> setConfiguration(
            @RequestParam float x,
            @RequestParam float y,
            @RequestParam float rot,
            @RequestParam String path
    ){
        mavLinkService.setScenarioConfigs(x, y, rot, path);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/sync")
    public ResponseEntity<Void> sync(
            @RequestParam float time
    ){
        mavLinkService.setScenarioStartTime(time);
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/stop")
    public ResponseEntity<Void> stop(){
        mavLinkService.stopScenario();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }

    @PostMapping("/reset")
    public ResponseEntity<Void> reset(){
        mavLinkService.resetScenario();
        return ResponseEntity
                .status(HttpStatus.NO_CONTENT)
                .body(null);
    }


}
