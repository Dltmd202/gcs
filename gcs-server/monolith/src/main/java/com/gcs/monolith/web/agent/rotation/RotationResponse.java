package com.gcs.monolith.web.agent.rotation;

import com.gcs.domain.rotation.Rotationable;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RotationResponse {

    public static RotationResponse defualtInstance = new RotationResponse(0.F, 0.F, 0.F);
    private Float roll;
    private Float pitch;
    private Float yaw;

    @Builder
    public RotationResponse(Float roll, Float pitch, Float yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }

    public RotationResponse(Rotationable rotationable){
        this.roll = rotationable.getRoll();
        this.pitch = rotationable.getPitch();
        this.yaw = rotationable.getYaw();
    }
}
