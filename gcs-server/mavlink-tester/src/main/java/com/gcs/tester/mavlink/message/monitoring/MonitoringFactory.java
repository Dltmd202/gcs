package com.gcs.tester.mavlink.message.monitoring;


import com.MAVLink.swarm.msg_monitoring;

public class MonitoringFactory {
    public static msg_monitoring make(int sysid){
        return new msg_monitoring(
                1L,
                0.F,
                0.F,
                0.F,
                1.0F,
                1.0F,
                1.0F,
                1.0F,
                0.F,
                0.F,
                0.F,
                2L,
                3L,
                2F,
                2F,
                2F,
                (short) 1,
                (short) 1,
                (short) 1,
                sysid,
                1,
                true
        );
    }

    public static msg_monitoring make(
            int sysid,
            float x,
            float y,
            float z
    ){
        return new msg_monitoring(
                1L,
                x,
                y,
                z,
                1.0F,
                1.0F,
                1.0F,
                1.0F,
                0.F,
                0.F,
                0.F,
                2L,
                3L,
                2F,
                2F,
                2F,
                (short) 1,
                (short) 1,
                (short) 1,
                sysid,
                1,
                true
        );
    }

    public static msg_monitoring make(
            int sysid,
            float x,
            float y,
            float z,
            float roll,
            float pitch,
            float yaw
    ){
        return new msg_monitoring(
                1L,
                x,
                y,
                z,
                1.0F,
                1.0F,
                1.0F,
                1.0F,
                roll,
                pitch,
                yaw,
                2L,
                3L,
                2F,
                2F,
                2F,
                (short) 1,
                (short) 1,
                (short) 1,
                sysid,
                1,
                true
        );
    }

    public static msg_monitoring make(
            int sysid,
            float x,
            float y,
            float z,
            float roll,
            float pitch,
            float yaw,
            float vx,
            float vy,
            float vz
    ){
        return new msg_monitoring(
                1L,
                x,
                y,
                z,
                1.0F,
                vx,
                vy,
                vz,
                roll,
                pitch,
                yaw,
                2L,
                3L,
                2F,
                2F,
                2F,
                (short) 1,
                (short) 1,
                (short) 1,
                sysid,
                1,
                true
        );
    }
}
