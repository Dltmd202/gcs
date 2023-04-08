package com.gcs.domain.agent;

import com.gcs.domain.rtk.RealTimeKinematic;
import com.gcs.domain.rtk.RealTimeLocatable;
import com.gcs.domain.rtk.RealTimeStatus;

public interface RealTimeAgent extends Agent, RealTimeKinematic {
    void update(RealTimeKinematic moveable);
    void update(RealTimeStatus status);

    void update(RealTimeLocatable realTimeLocatable);
}
