package com.gcs.monolith.web.agent.velocity.dto;

import com.gcs.domain.velocity.Velociterable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class VelocityResponse {
    public static VelocityResponse defaultInstance = new VelocityResponse(0.F, 0.F, 0.F);
    public Float vx;
    public Float vy;
    public Float vz;

    @Builder
    public VelocityResponse(Float vx, Float vy, Float vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    public VelocityResponse(Velociterable velociterable){
        this.vx = velociterable.getVx();
        this.vy = velociterable.getVy();
        this.vz = velociterable.getVz();
    }
}
