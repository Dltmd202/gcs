package com.gcs.domain.agent;

import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;

public interface Agent extends AgentConfigureable, Moveable {
    void update(Velociterable velocity);
    void update(LlhLocatable llhLocatable);
    void update(NedLocatable nedLocatable);
    void update(Rotationable turnable);
    void update(Flyable flyStatus);
    void update(Moveable moveable);

}