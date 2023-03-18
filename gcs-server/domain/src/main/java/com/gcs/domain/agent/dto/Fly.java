package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.Flyable;
import com.gcs.domain.coordinate.Locateable;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;

public class Fly implements Flyable {
    private Rotationable angle;
    private Locateable locate;

    @Override
    public Rotationable getAngle() {
        return angle;
    }

    @Override
    public LlhLocatable getLlh() {
        return locate.getLlh();
    }

    @Override
    public NedLocatable getNed() {
        return getNed();
    }

    @Override
    public float getLat() {
        return locate.getLat();
    }

    @Override
    public float getLng() {
        return locate.getLng();
    }

    @Override
    public float getAlt() {
        return locate.getAlt();
    }

    @Override
    public float getX() {
        return locate.getX();
    }

    @Override
    public float getY() {
        return locate.getY();
    }

    @Override
    public float getZ() {
        return locate.getZ();
    }

    @Override
    public float getRoll() {
        return angle.getRoll();
    }

    @Override
    public float getPitch() {
        return angle.getPitch();
    }

    @Override
    public float getYaw() {
        return angle.getYaw();
    }
}
