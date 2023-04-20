package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import com.MAVLink.common.*;
import com.MAVLink.minimal.msg_heartbeat;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.tester.mavlink.message.MAVLinkUtils;

import java.io.IOException;
import java.net.*;

public class Receiver {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        SocketAddress me = new InetSocketAddress("192.168.35.3", 9750);
        SocketAddress drone = new InetSocketAddress("127.0.0.1", 9750);
        InetAddress addr = InetAddress.getByName("127.0.0.1");

        DatagramSocket serverSocket = new DatagramSocket(me);

        byte[] buf = new byte[256];

        DatagramPacket dronePacket = new DatagramPacket(buf, buf.length);

        Parser parser = new Parser();
        int cnt = 0;

        try {
            while (true){
                synchronized (serverSocket) {
                    serverSocket.receive(dronePacket);

                    byte[] data = dronePacket.getData();

                    int length = dronePacket.getLength();

                    MAVLinkMessage mavLinkMessage = MAVLinkUtils.getMessage(data, length)
                            .orElseThrow(() -> new IllegalArgumentException());

                    if(mavLinkMessage instanceof msg_attitude)
                        continue;
                    if(mavLinkMessage instanceof msg_attitude_quaternion)
                        continue;
                    if(mavLinkMessage instanceof msg_servo_output_raw)
                        continue;
                    if(mavLinkMessage instanceof msg_set_attitude_target)
                        continue;
                    if(mavLinkMessage instanceof msg_vfr_hud)
                        continue;
                    if(mavLinkMessage instanceof msg_position_target_local_ned)
                        continue;
                    if(mavLinkMessage instanceof msg_global_position_int)
                        continue;
                    if(mavLinkMessage instanceof msg_local_position_ned)
                        continue;
                    if(mavLinkMessage instanceof msg_attitude_target)
                        continue;
                    if(mavLinkMessage instanceof msg_attitude)
                        continue;
                    if(mavLinkMessage instanceof msg_altitude)
                        continue;
                    if(mavLinkMessage instanceof msg_extended_sys_state)
                        continue;
                    if(mavLinkMessage instanceof msg_gps_raw_int)
                        continue;
                    if(mavLinkMessage instanceof msg_battery_status)
                        continue;
                    if(mavLinkMessage instanceof msg_estimator_status)
                        continue;
                    if(mavLinkMessage instanceof msg_ping)
                        continue;
                    if(mavLinkMessage instanceof msg_link_node_status)
                        continue;
                    if(mavLinkMessage instanceof msg_sys_status)
                        continue;
                    if(mavLinkMessage instanceof msg_home_position)
                        continue;
                    if(mavLinkMessage instanceof msg_heartbeat)
                        continue;
                    if(mavLinkMessage instanceof msg_vibration)
                        continue;

                    if(mavLinkMessage instanceof msg_param_value){
                        msg_param_value param = (msg_param_value) mavLinkMessage;

                        System.out.println(param);
                        String s = new String(param.param_id);
                        System.out.println(s);
                    }


                    cnt++;
                }

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(cnt);
        }
    }
}
