package com.gcs.domain.agent.service;

import com.gcs.domain.coordinate.ned.NedLocatable;

public interface AgentRealTimeOperationService {
    void updateRtkCoordinate(int sysid, NedLocatable coordinate);
}
