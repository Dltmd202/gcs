package com.gcs.domain.agent.dto;

import com.gcs.domain.agent.Agent;
import com.gcs.domain.agent.Flyable;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.coordinate.llh.LlhCoordinate;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotation;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;
import lombok.Builder;

import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author Dltmd202
 */
public class AgentDto implements Agent {
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
    private final String color;
    private final String complementaryColor;

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
    public static AgentDto makeAgent(int sysid, String ip, int port){
        int r = ThreadLocalRandom.current().nextInt(0xff + 1);
        int g = ThreadLocalRandom.current().nextInt(0xff + 1);
        int b = ThreadLocalRandom.current().nextInt(0xff + 1);

        String color = String.format("#%02x%02x%02x", r, g, b);
        String complementaryColor = String.format("#%02x%02x%02x", 255 - r, 255 - g, 255 - b);
        AgentDto agent = AgentDto.builder()
                .sysid(sysid)
                .ip(ip)
                .port(port)
                .color(color)
                .complementaryColor(complementaryColor)
                .build();

        LlhLocatable llh = new LlhCoordinate(0, 0, 0);
        NedLocatable ned = new NedCoordinate(0, 0, 0);
        Rotationable rotate = new Rotation(0, 0, 0);

        agent.update(llh);
        agent.update(ned);
        agent.update(rotate);

        return agent;
    }

    @Builder
    public AgentDto(
            Integer sysid,
            String mode,
            String type,
            Integer group,
            String vehicle,
            String ip,
            Integer port,
            String color,
            String complementaryColor
    ) {
        this.sysid = sysid;
        this.mode = mode;
        this.type = type;
        this.group = group;
        this.vehicle = vehicle;
        this.ip = ip;
        this.port = port;
        this.color = color;
        this.complementaryColor = complementaryColor;
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
}
