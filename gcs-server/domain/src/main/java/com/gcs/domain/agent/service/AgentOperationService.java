package com.gcs.domain.agent.service;

import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;

public interface AgentOperationService {
    void updateLlhCoordinate(int sysid, LlhLocatable coordinate);
    void updateNedCoordinate(int sysid, NedLocatable coordinate);
    void updateFlyStatus(int sysid, Flyable fly);
    void updateMove(int sysid, Moveable move);
    void updateAngle(int sysid, Rotationable angle);
    void updateVelocity(int sysid, Velociterable velocity);

}
