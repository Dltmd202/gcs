package com.gcs.api.web.agent.dto;

import com.gcs.domain.agent.dto.AgentDto;
import com.gcs.api.web.agent.coordinate.llh.dto.LlhResponse;
import com.gcs.api.web.agent.coordinate.ned.dto.NedResponse;
import com.gcs.api.web.agent.rotation.RotationResponse;
import com.gcs.api.web.agent.velocity.dto.VelocityResponse;
import com.gcs.supporter.mavlink.param.DroneShow;
import lombok.Getter;

import java.util.HashMap;
import java.util.List;
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
    private VelocityResponse velocity;
    private RotationResponse angle;
    private String color;
    private String complementaryColor;
    private Long tow;
    private Boolean active = false;
    private Map<String, Object> param = new HashMap<>();

    public AgentResponse(AgentDto agent){
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
        this.velocity = Objects.nonNull(agent.getVelocity()) ?
                new VelocityResponse(agent.getVelocity()) :
                VelocityResponse.defaultInstance;

        this.angle = Objects.nonNull(agent.getAngle()) ?
                new RotationResponse(agent.getAngle()) :
                RotationResponse.defualtInstance;

        this.color = agent.getColor();
        this.complementaryColor = agent.getComplementaryColor();

        if(Objects.nonNull(agent.getNed()) || Objects.nonNull(agent.getLlh())){
            this.active = true;
        }

        param = new HashMap<>();

        for (DroneShow value : DroneShow.values()) {
            param.put(value.getValue(), Float.NaN);
        }
    }

    public AgentResponse(){}
}
