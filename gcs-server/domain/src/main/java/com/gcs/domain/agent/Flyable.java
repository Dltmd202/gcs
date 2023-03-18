package com.gcs.domain.agent;

import com.gcs.domain.coordinate.Locateable;
import com.gcs.domain.rotation.Rotationable;

public interface Flyable extends Locateable, Rotationable {
    Rotationable getAngle();

    default float getRoll() {
        return getAngle().getRoll();
    }

    default float getPitch(){
        return getAngle().getPitch();
    }

    default float getYaw(){
        return getAngle().getYaw();
    }
}
