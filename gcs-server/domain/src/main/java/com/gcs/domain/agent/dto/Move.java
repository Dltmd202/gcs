package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;

public class Move implements Moveable {
    private Velociterable velocity;
    private Flyable fly;
    @Override
    public Rotationable getAngle() {
        return fly.getAngle();
    }

    @Override
    public Velociterable getVelocity() {
        return velocity;
    }

    @Override
    public LlhLocatable getLlh() {
        return fly.getLlh();
    }

    @Override
    public NedLocatable getNed() {
        return fly.getNed();
    }

    @Override
    public float getLat() {
        return fly.getLat();
    }

    @Override
    public float getLng() {
        return fly.getLng();
    }

    @Override
    public float getAlt() {
        return fly.getAlt();
    }

    @Override
    public float getX() {
        return fly.getX();
    }

    @Override
    public float getY() {
        return fly.getY();
    }

    @Override
    public float getZ() {
        return fly.getZ();
    }

    @Override
    public float getRoll() {
        return fly.getRoll();
    }

    @Override
    public float getPitch() {
        return fly.getPitch();
    }

    @Override
    public float getYaw() {
        return fly.getY();
    }

    @Override
    public float getVx() {
        return velocity.getVx();
    }

    @Override
    public float getVy() {
        return velocity.getVy();
    }

    @Override
    public float getVz() {
        return velocity.getVz();
    }
}
