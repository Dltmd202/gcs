package com.gcs.monolith.socket.agent.controller;

import com.gcs.monolith.socket.coordinate.request.CoordinateRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.annotation.SendToUser;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/realtime")
public class AgentRealTimeController {

    @MessageMapping("/add")
    @SendToUser("/sub/info")
    public String addCoordinate(CoordinateRequest request){
        log.debug("addCoordinate {}", request.toString());
        return request.toString();
    }
}
