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

public class MonitoringSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress me = new InetSocketAddress("127.0.0.1", 9755);
        SocketAddress dest = new InetSocketAddress("127.0.0.1", 9750);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        int i = 0;
        MAVLinkMessage monitoring = new msg_monitoring();
        for (i = 0; i < 1; i++) {
            monitoring = MonitoringFactory.make(
                    5,
                    0.0F + 0.0F,
                    0.0F + 0.0F,
                    -3.0F - 0.5F,
                    -0.0F,
                    0.0F + 0.00F,
                    -45.00F
            );

            byte[] data;
            DatagramPacket packet;
            data = MAVLinkUtils.getPacketData(monitoring);
            packet = new DatagramPacket(data, data.length, dest);
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