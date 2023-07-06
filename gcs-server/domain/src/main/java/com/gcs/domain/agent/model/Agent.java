package com.gcs.domain.agent.model;

import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.rtk.RealTimeKinematic;
import com.gcs.domain.velocity.Velociterable;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Getter @Setter
@NoArgsConstructor
public class Agent {
    private Integer id;
    private Integer sysid;
    private String mode;
    private String type;
    private Integer group;
    private String vehicle;
    private String ip;
    private Integer port;
    private LlhLocatable llh;
    private NedLocatable ned;
    private NedLocatable rtkCoordinate;
    private NedLocatable destination = new NedCoordinate(0, 0, 0);
    private short baseSatelliteCount;
    private short roverSatelliteCount;
    private float destYaw = 0.F;
    private Rotationable angle;
    private Velociterable velocity;
    private RealTimeKinematic rtk;
    private int status1;
    private int status2;
    private String LEDColor;
    private final Map<String, Parameter> parameters = new ConcurrentHashMap<>();

    @Builder
    public Agent(
            Integer id,
            Integer sysid,
            String mode,
            String type,
            Integer group,
            String vehicle,
            String ip,
            Integer port,
            Integer status1,
            Integer status2,
            LlhLocatable llh,
            NedLocatable ned,
            NedLocatable destination,
            Rotationable angle,
            Velociterable velocity
    ) {
        this.id = id;
        this.sysid = sysid;
        this.mode = mode;
        this.type = type;
        this.group = group;
        this.vehicle = vehicle;
        this.ip = ip;
        this.port = port;
        this.llh = llh;
        this.ned = ned;
        this.destination = destination;
        this.angle = angle;
        this.velocity = velocity;
        this.status1 = status1;
        this.status2 = status2;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agent agent = (Agent) o;
        return Objects.equals(sysid, agent.sysid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysid);
    }

    public void update(Velociterable velocity) {
        this.velocity = velocity;
    }

    public void update(LlhLocatable llh) {
        this.llh = llh;
    }

    public void update(NedLocatable ned) {
        this.ned = ned;
    }

    public void update(Rotationable angle) {
        this.angle = angle;
    }

    public void update(Flyable flyStatus) {
        this.angle = flyStatus.getAngle();
        this.ned = flyStatus.getNed();
        this.llh = flyStatus.getLlh();
    }

    public void update(Moveable moveable) {
        this.angle = moveable.getAngle();
        this.ned = moveable.getNed();
        this.llh = moveable.getLlh();
        this.velocity = moveable.getVelocity();
    }

    public void update(Parameter parameter){
        parameters.put(parameter.getParamId(), parameter);
    }

    public LlhLocatable getLlh() {
        return llh;
    }

    public NedLocatable getNed() {
        return ned;
    }

    public Rotationable getAngle() {
        return angle;
    }

    public Velociterable getVelocity() {
        return velocity;
    }

    public NedLocatable getDestination() {
        return destination;
    }

    public void setDestination(NedLocatable destination) {
        this.destination = destination;
    }

    public void update(RealTimeKinematic moveable) {
        this.rtkCoordinate = moveable.getRtkCoordinate();
    }

    public NedLocatable getRtkCoordinate() {
        return rtk.getRtkCoordinate();
    }

}
