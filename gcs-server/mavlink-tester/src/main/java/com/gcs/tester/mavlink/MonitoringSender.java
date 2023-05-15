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
        SocketAddress me = new InetSocketAddress("127.0.0.1", 9752);
        SocketAddress dest = new InetSocketAddress("127.0.0.1", 9750);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        int i = 0;

        MAVLinkMessage monitoring = new msg_monitoring();
        for (i = 0; i < 100; i++) {
            monitoring = MonitoringFactory.make(
                    1,
                    30.4F,
                    26.1F,
                    0F,
                    0.0F + 0.0F,
                    0.0F + 0.0F,
                    0.0F,
                    -0.0F,
                    0.0F + 0.00F,
                    -45.00F,
                    1 << 9 | 1 << 17 | 1 << 18,
                    0x00,
                    0x00,
                    0x00,
                    0x00,
                    10000000 + i * 1000
            );

            byte[] data;
            DatagramPacket packet;
            data = MAVLinkUtils.getPacketData(monitoring);
            packet = new DatagramPacket(data, data.length, dest);
            socket.send(packet);

            monitoring = MonitoringFactory.make(
                    2,
                    30.4F,
                    26.1F,
                    0F,
                    0.0F + 0.0F,
                    0.0F + 0.0F,
                    0.0F,
                    -0.0F,
                    0.0F + 0.00F,
                    -45.00F,
                    1 << 9 | 1 << 17 | 1 << 18,
                    0x00,
                    0x00,
                    0x00,
                    0x00,
                    10000000 + i * 1000
            );

            data = MAVLinkUtils.getPacketData(monitoring);
            packet = new DatagramPacket(data, data.length, dest);
            socket.send(packet);
            Thread.sleep(1000);
        }

    }

}