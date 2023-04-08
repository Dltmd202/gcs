package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.supporter.mavlink.message.factory.MavLinkMessageFactory;
import com.gcs.tester.mavlink.message.MAVLinkUtils;
import com.gcs.tester.mavlink.message.monitoring.MonitoringFactory;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
import java.net.SocketAddress;

public class Sender2 {
    public static void main(String[] args) throws IOException, InterruptedException {
        SocketAddress me = new InetSocketAddress("10.42.0.3", 9750);
        SocketAddress drone = new InetSocketAddress("10.42.0.210", 9750);

        DatagramSocket socket = new DatagramSocket(me);
        StringBuilder sb = new StringBuilder();

        MAVLinkMessage arm = MavLinkMessageFactory.armMessage(110);
        MAVLinkMessage scenarioConfigs = MavLinkMessageFactory.setScenarioConfigs(110, 0, 0, 0, "test/node_1.txt");
        MAVLinkMessage setScenarioStartTime = MavLinkMessageFactory.setScenarioStartTime(110, 2);

        System.out.println(arm);
        System.out.println(scenarioConfigs);
        System.out.println(setScenarioStartTime);

        byte[] data;
        DatagramPacket packet;


        data = MAVLinkUtils.getPacketData(arm);
        packet = new DatagramPacket(data, data.length, drone);
        socket.send(packet);

        Thread.sleep(1000);


        data = MAVLinkUtils.getPacketData(scenarioConfigs);
        packet = new DatagramPacket(data, data.length, drone);
        socket.send(packet);

        Thread.sleep(1000);

        data = MAVLinkUtils.getPacketData(setScenarioStartTime);
        packet = new DatagramPacket(data, data.length, drone);
        socket.send(packet);




    }

}