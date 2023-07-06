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
        int cnt = 0;
        for (i = 0; i < 100; i++) {
            System.out.print(i * 1 + " ");
            long start = System.currentTimeMillis();
            for (int j = 2; j <= 200; j++) {
                monitoring = MonitoringFactory.make(
                        j,
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
                        j,
                        0.5F * i,
                        0,
                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
                        0.0F + 0.1F * (ThreadLocalRandom.current().nextInt() % 100),
//                        j, j, j, j, j, j,
                        -0.0F,
                        0.0F + 0.00F,
                        -10.0F,
                        1 << 9 | 1 << 17 | 1 << 18,
//                        1,
                        0x00,
                        0x00,
                        0xff,
                        0x00,
                        i * 1000
                );

                byte[] data;
                DatagramPacket packet;
                data = MAVLinkUtils.getPacketData(monitoring);
                packet = new DatagramPacket(data, data.length, dest);
                socket.send(packet);
                cnt++;
            }
            long end = System.currentTimeMillis();
            System.out.println("Waiting " + (1000 - (end - start)) + " cnt = " + cnt);
            Thread.sleep(Math.max(0, 1000 - (end - start)));
        }

    }

}