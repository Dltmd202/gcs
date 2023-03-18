package com.gcs.tester.mavlink;

import com.MAVLink.common.msg_command_long;
import com.gcs.tester.mavlink.message.MAVLinkUtils;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

import static com.MAVLink.enums.MAV_CMD.MAV_CMD_COMPONENT_ARM_DISARM;
import static com.MAVLink.enums.MAV_CMD.MAV_CMD_NAV_TAKEOFF;

public class TargetSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress me = new InetSocketAddress("192.168.35.37", 9751);
        SocketAddress server = new InetSocketAddress("192.168.35.229", 9751);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        msg_command_long arm = armMessage();
        msg_command_long takeoff = takeoff();

        byte[] data;
        DatagramPacket packet;
        data = MAVLinkUtils.getPacketData(arm);
        packet = new DatagramPacket(data, data.length, server);
        socket.send(packet);

        data = MAVLinkUtils.getPacketData(takeoff);
        packet = new DatagramPacket(data, data.length, server);
        socket.send(packet);

    }

    private static msg_command_long armMessage() {
        msg_command_long arm = new msg_command_long();
        arm.sysid = 1;

        arm.target_system = (short) 1;
        arm.command = MAV_CMD_COMPONENT_ARM_DISARM;
        arm.confirmation = 1;
        arm.param1 = 1.0F;

        arm.isMavlink2 = true;
        return arm;
    }

    private static msg_command_long takeoff() {
        msg_command_long msg = new msg_command_long();
        msg.sysid = 1;

        msg.target_system = (short) 1;
        msg.target_component = (short) 1;
        msg.command = MAV_CMD_NAV_TAKEOFF;
        msg.confirmation = 1;
        msg.param1 = -1;
        msg.param4 = Float.NaN;
        msg.param5 = Float.NaN;
        msg.param6 = Float.NaN;
        msg.param7 = 497.996F;

        msg.isMavlink2 = true;
        return msg;
    }
}
