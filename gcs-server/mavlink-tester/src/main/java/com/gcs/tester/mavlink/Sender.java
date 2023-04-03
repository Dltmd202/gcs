package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.tester.mavlink.message.MAVLinkUtils;
import com.gcs.tester.mavlink.message.monitoring.MonitoringFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Sender {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress me = new InetSocketAddress("192.168.35.219", 9755);
        SocketAddress server = new InetSocketAddress("192.168.35.219", 9756);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        MAVLinkMessage monitoring = new msg_monitoring();
        for (i = 0; i < 1000000; i++) {
            monitoring = MonitoringFactory.make(
                    1,
                    1.0F + 0.1F,
                    2.0F + 0.1F * i,
                    -2.0F - 0.1F,
                    0.0F,
                    0.0F + 0.05F,
                    0F
            );

            byte[] data;
            DatagramPacket packet;
            data = MAVLinkUtils.getPacketData(monitoring);
            packet = new DatagramPacket(data, data.length, server);
            socket.send(packet);
//            System.out.println(monitoring.toString());
//
//            for (byte b : data) {
//                System.out.printf("%02x ", b);
//            }
//            System.out.println();


//            Thread.sleep(15);
        }

    }

}