package com.gcs.domain.agent;

import com.gcs.domain.velocity.Velociterable;

public interface Moveable extends Flyable, Velociterable {
    Velociterable getVelocity();

    @Override
    default float getVx() {
        return getVelocity().getVx();
    }

    @Override
    default float getVy(){
        return getVelocity().getVy();
    }

    default float getVz(){
        return getVelocity().getVz();
    }
}
