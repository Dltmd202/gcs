package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.agent.RealTimeAgent;
import com.gcs.domain.coordinate.llh.LlhCoordinate;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotation;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.rtk.RealTimeKinematic;
import com.gcs.domain.rtk.RealTimeLocatable;
import com.gcs.domain.rtk.RealTimeStatus;
import com.gcs.domain.velocity.Velociterable;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Dltmd202
 */
public class AgentDto implements RealTimeAgent {
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
    private NedLocatable destination;
    private Rotationable angle;
    private Velociterable velocity;
    private RealTimeKinematic rtk;
    private int status1;
    private int status2;
    private String LEDColor;
    private final String color;
    private final String complementaryColor;
    private short baseSatelliteCount;
    private short roverSatelliteCount;

    public void setSysid(Integer sysid) {
        this.sysid = sysid;
    }
    public void setMode(String mode) {
        this.mode = mode;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setGroup(Integer group) {
        this.group = group;
    }

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public void setId(Integer id){
        this.id = id;
    }

    @Builder
    public AgentDto(
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
        int r = ThreadLocalRandom.current().nextInt(0xff + 1);
        int g = ThreadLocalRandom.current().nextInt(0xff + 1);
        int b = ThreadLocalRandom.current().nextInt(0xff + 1);

        this.color = String.format("#%02x%02x%02x", r, g, b);
        this.complementaryColor = String.format("#%02x%02x%02x", 255 - r, 255 - g, 255 - b);
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
    public Integer getPort() {
        return port;
    }

    public AgentDto(){
        int r = ThreadLocalRandom.current().nextInt(0xff + 1);
        int g = ThreadLocalRandom.current().nextInt(0xff + 1);
        int b = ThreadLocalRandom.current().nextInt(0xff + 1);

        this.color = String.format("#%02x%02x%02x", r, g, b);
        this.complementaryColor = String.format("#%02x%02x%02x", 255 - r, 255 - g, 255 - b);
        this.llh = new LlhCoordinate(0, 0, 0);
        this.ned = new NedCoordinate(0, 0, 0);
        this.angle = new Rotation(0, 0, 0);
        this.destination = new NedCoordinate(0, 0, 0);
    }

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public Integer getSysid() {
        return sysid;
    }

    @Override
    public String getMode() {
        return mode;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    public Integer getGroup() {
        return group;
    }

    @Override
    public String getVehicle() {
        return vehicle;
    }

    @Override
    public String getIp() {
        return ip;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AgentDto agent = (AgentDto) o;
        return Objects.equals(sysid, agent.sysid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sysid);
    }

    @Override
    public void update(Velociterable velocity) {
        this.velocity = velocity;
    }

    @Override
    public void update(LlhLocatable llh) {
        this.llh = llh;
    }

    @Override
    public void update(NedLocatable ned) {
        this.ned = ned;
    }

    @Override
    public void update(Rotationable angle) {
        this.angle = angle;
    }

    public String getColor() {
        return color;
    }

    public String getComplementaryColor() {
        return complementaryColor;
    }

    @Override
    public void update(Flyable flyStatus) {
        this.angle = flyStatus.getAngle();
        this.ned = flyStatus.getNed();
        this.llh = flyStatus.getLlh();
    }

    @Override
    public void update(Moveable moveable) {
        this.angle = moveable.getAngle();
        this.ned = moveable.getNed();
        this.llh = moveable.getLlh();
        this.velocity = moveable.getVelocity();
    }

    @Override
    public LlhLocatable getLlh() {
        return llh;
    }

    @Override
    public NedLocatable getNed() {
        return ned;
    }

    @Override
    public Rotationable getAngle() {
        return angle;
    }

    @Override
    public Velociterable getVelocity() {
        return velocity;
    }

    public NedLocatable getDestination() {
        return destination;
    }

    public void setDestination(NedLocatable destination) {
        this.destination = destination;
    }

    @Override
    public void update(RealTimeKinematic moveable) {
        this.rtk = moveable;
    }

    @Override
    public void update(RealTimeStatus status) {

    }

    @Override
    public void update(RealTimeLocatable realTimeLocatable) {

    }

    @Override
    public NedLocatable getRtkCoordinate() {
        return rtk.getRtkCoordinate();
    }

    @Override
    public short getBaseSatelliteCount() {
        return baseSatelliteCount;
    }

    @Override
    public short getRoverSatelliteCount() {
        return roverSatelliteCount;
    }
}
