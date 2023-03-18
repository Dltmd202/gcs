package com.gcs.domain.rotation;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Rotation implements Rotationable{
    private float roll;
    private float pitch;
    private float yaw;

    @Builder
    public Rotation(float roll, float pitch, float yaw) {
        this.roll = roll;
        this.pitch = pitch;
        this.yaw = yaw;
    }
}
