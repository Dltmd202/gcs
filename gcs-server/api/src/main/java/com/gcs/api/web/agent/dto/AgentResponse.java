package com.gcs.api.web.agent.dto;

import com.gcs.api.web.agent.led.LedResponse;
import com.gcs.domain.agent.model.Agent;
import com.gcs.api.web.agent.coordinate.llh.dto.LlhResponse;
import com.gcs.api.web.agent.coordinate.ned.dto.NedResponse;
import com.gcs.api.web.agent.rotation.RotationResponse;
import com.gcs.api.web.agent.velocity.dto.VelocityResponse;
import com.gcs.supporter.mavlink.param.DroneShow;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Getter
public class AgentResponse {
    private Integer id;
    private Integer sysid;
    private String mode;
    private String type;
    private Integer group;
    private String vehicle;
    private String ip;
    private Integer battery;
    private NedResponse ned;
    private LlhResponse llh;
    private NedResponse rtk;
    private RotationResponse angle;
    private LedResponse led;
    private Long tow;
    private Boolean active = true;

    public AgentResponse(Agent agent){
        this.id = agent.getId();
        this.sysid = agent.getSysid();
        this.mode = agent.getMode();
        this.type = agent.getType();
        this.group = agent.getGroup();
        this.vehicle = agent.getVehicle();
        this.ip = agent.getIp();
        this.battery = 0;
        this.tow = 0L;

        this.ned = Objects.nonNull(agent.getNed()) ? new NedResponse(agent.getNed()) : NedResponse.defaultInstance;
        this.rtk = NedResponse.defaultInstance;
        this.llh = Objects.nonNull(agent.getLlh()) ? new LlhResponse(agent.getLlh()) : LlhResponse.defaultInstance;
        this.led = new LedResponse(-1, -1, -1);

        this.angle = Objects.nonNull(agent.getAngle()) ?
                new RotationResponse(agent.getAngle()) :
                RotationResponse.defualtInstance;

        if(Objects.nonNull(agent.getNed()) || Objects.nonNull(agent.getLlh())){
            this.active = true;
        }
    }

    public AgentResponse(){}
}
