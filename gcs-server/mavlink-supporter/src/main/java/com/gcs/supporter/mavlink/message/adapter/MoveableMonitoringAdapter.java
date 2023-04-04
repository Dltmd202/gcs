package com.gcs.supporter.mavlink.message.adapter;

import com.MAVLink.swarm.msg_monitoring;
import com.gcs.domain.agent.Moveable;
import com.gcs.domain.coordinate.llh.LlhLocatable;
import com.gcs.domain.coordinate.ned.NedCoordinate;
import com.gcs.domain.coordinate.ned.NedLocatable;
import com.gcs.domain.rotation.Rotation;
import com.gcs.domain.rotation.Rotationable;
import com.gcs.domain.velocity.Velociterable;
import com.gcs.domain.velocity.Velocity;

import java.util.Objects;

public class MoveableMonitoringAdapter implements Moveable {
    private final msg_monitoring msg;
    private Rotationable angle;
    private Velociterable velocity;
    private LlhLocatable llh;
    private NedLocatable ned;


    public MoveableMonitoringAdapter(msg_monitoring msg) {
        this.msg = msg;
    }

    @Override
    public Rotationable getAngle() {
        if(Objects.isNull(angle))
            this.angle = Rotation.builder()
                    .roll(msg.roll)
                    .pitch(msg.pitch)
                    .yaw(msg.yaw)
                    .build();
        return angle;
    }

    @Override
    public Velociterable getVelocity() {
        if(Objects.isNull(velocity))
            this.velocity = Velocity.builder()
                    .vx(msg.vx)
                    .vy(msg.vy)
                    .vz(msg.vz)
                    .build();
        return velocity;
    }

    /**
     *  대채할 지료가 필요함
     * @return
     */
    @Override
    public LlhLocatable getLlh() {
        return null;
    }


    @Override
    public NedLocatable getNed() {
        if(Objects.isNull(ned))
            this.ned = NedCoordinate.builder()
                    .x(msg.pos_x)
                    .y(msg.pos_y)
                    .z(msg.pos_z)
                    .build();
        return ned;
    }

    @Override
    public float getLat() {
        return getLlh().getLat();
    }

    @Override
    public float getLng() {
        return getLlh().getLng();
    }

    @Override
    public float getAlt() {
        return getLlh().getAlt();
    }

    @Override
    public float getX() {
        return getNed().getX();
    }

    @Override
    public float getY() {
        return getNed().getY();
    }

    @Override
    public float getZ() {
        return getNed().getZ();
    }

    @Override
    public float getRoll() {
        return getAngle().getRoll();
    }

    @Override
    public float getPitch() {
        return getAngle().getPitch();
    }

    @Override
    public float getYaw() {
        return getAngle().getYaw();
    }

    @Override
    public float getVx() {
        return getVelocity().getVx();
    }

    @Override
    public float getVy() {
        return getVelocity().getVy();
    }

    @Override
    public float getVz() {
        return getVelocity().getVz();
    }
}
