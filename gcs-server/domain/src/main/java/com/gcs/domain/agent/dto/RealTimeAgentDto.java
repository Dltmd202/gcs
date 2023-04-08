package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.RealTimeAgent;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rtk.RealTimeKinematic;
import com.gcs.domain.rtk.RealTimeLocatable;
import com.gcs.domain.rtk.RealTimeStatus;

public class RealTimeAgentDto extends AgentDto implements RealTimeAgent {

    private NedLocatable rtkCoordinate;
    private short baseSatelliteCount;
    private short roverSatelliteCount;

    public RealTimeAgentDto(){
        super();
        this.rtkCoordinate = new NedCoordinate(0, 0, 0);
    }

    @Override
    public NedLocatable getRtkCoordinate() {
        return rtkCoordinate;
    }

    @Override
    public short getBaseSatelliteCount() {
        return baseSatelliteCount;
    }

    @Override
    public short getRoverSatelliteCount() {
        return roverSatelliteCount;
    }

    @Override
    public void update(RealTimeKinematic rtk) {
        this.rtkCoordinate = rtk.getRtkCoordinate();
        this.baseSatelliteCount = rtk.getBaseSatelliteCount();
        this.roverSatelliteCount = rtk.getRoverSatelliteCount();
    }

    @Override
    public void update(RealTimeStatus status) {
        this.baseSatelliteCount = status.getBaseSatelliteCount();
        this.roverSatelliteCount = status.getRoverSatelliteCount();
    }

    @Override
    public void update(RealTimeLocatable realTimeLocatable) {
        this.rtkCoordinate = realTimeLocatable.getRtkCoordinate();
    }
}
