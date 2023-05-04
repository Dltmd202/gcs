package com.gcs.tester.mavlink.message.monitoring;


import com.MAVLink.swarm.msg_monitoring;

public class MonitoringFactory {


    public static msg_monitoring make(
            int sysid,
            float x,
            float y,
            float z,
            float roll,
            float pitch,
            float yaw,
            int status1,
            int status2,
            int r,
            int g,
            int b
    ){
        return new msg_monitoring(
                1L,
                x,
                y,
                z,
                yaw,
                roll,
                pitch,
                status1,
                status2,
                x,
                y,
                z,
                (short) 0,
                (short) 0,
                (short) 99,
                (short) r,
                (short) g,
                (short)b ,
                sysid,
                0,
                true
        );
    }

}
