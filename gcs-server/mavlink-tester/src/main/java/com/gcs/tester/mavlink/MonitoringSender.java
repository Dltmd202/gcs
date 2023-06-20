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
import java.util.concurrent.ThreadLocalRandom;

public class MonitoringSender {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress me = new InetSocketAddress("127.0.0.1", 9752);
        SocketAddress dest = new InetSocketAddress("127.0.0.1", 9750);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        int i = 0;

        MAVLinkMessage monitoring = new msg_monitoring();
        for (i = 0; i < 1000; i++) {
            System.out.println(i * 1000);
            for (int j = 1; j <= 5; j++) {
                monitoring = MonitoringFactory.make(
                        j,
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
                        j, j, j, j, j, j,
                        -0.0F,
                        0.0F + 0.00F,
                        -10.0F,
//                        1 << 9 | 1 << 17 | 1 << 18,
                        1,
                        0x00,
                        0xff,
                        0x00,
                        0x00,
                        i * 1000
                );

                byte[] data;
                DatagramPacket packet;
                data = MAVLinkUtils.getPacketData(monitoring);
                packet = new DatagramPacket(data, data.length, dest);
                socket.send(packet);
            }

            for (int j = 6; j <= 10; j++) {
                monitoring = MonitoringFactory.make(
                        j,
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
                        j - 10, j - 8, j - 10, j - 10, j - 10, j - 10,
                        -0.0F,
                        0.0F + 0.00F,
                        -10.0F,
                        1 << 9 | 1 << 17 | 1 << 18 | 1 << 25 | 1 << 26,
//                        1,
                        0x00,
                        0xff,
                        0x00,
                        0x00,
                        i * 1000
                );

                byte[] data;
                DatagramPacket packet;
                data = MAVLinkUtils.getPacketData(monitoring);
                packet = new DatagramPacket(data, data.length, dest);
                socket.send(packet);
            }

            for (int j = 11; j <= 15; j++) {
                monitoring = MonitoringFactory.make(
                        j,
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
                        j - 10, j - 6, j - 10, j - 10, j - 10, j - 10,
                        -0.0F,
                        0.0F + 0.00F,
                        -10.0F,
                        1 << 9 | 1 << 17 | 1 << 25,
//                        1,
                        0x00,
                        0xff,
                        0x00,
                        0x00,
                        i * 1000
                );

                byte[] data;
                DatagramPacket packet;
                data = MAVLinkUtils.getPacketData(monitoring);
                packet = new DatagramPacket(data, data.length, dest);
                socket.send(packet);
            }

            for (int j = 16; j <= 20; j++) {
                monitoring = MonitoringFactory.make(
                        j,
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 10),
                        j - 10, j - 6, j - 10, j - 10, j - 10, j - 10,
                        -0.0F,
                        0.0F + 0.00F,
                        -10.0F,
                        1 << 9,
//                        1,
                        0x00,
                        0xff,
                        0x00,
                        0x00,
                        i * 1000
                );

                byte[] data;
                DatagramPacket packet;
                data = MAVLinkUtils.getPacketData(monitoring);
                packet = new DatagramPacket(data, data.length, dest);
                socket.send(packet);
            }
            Thread.sleep(1000);
        }

    }

}