package com.gcs.domain.velocity;

import lombok.Builder;

public class Velocity implements Velociterable{
    private float vx;
    private float vy;
    private float vz;

    @Builder
    public Velocity(float vx, float vy, float vz) {
        this.vx = vx;
        this.vy = vy;
        this.vz = vz;
    }

    @Override
    public float getVx() {
        return vx;
    }

    @Override
    public float getVy() {
        return vy;
    }

    @Override
    public float getVz() {
        return vz;
    }
}
