package com.gcs.tester.mavlink;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.tester.mavlink.message.MAVLinkUtils;

import java.io.IOException;
import java.net.*;

public class Receiver {
    public static void main(String[] args) throws UnknownHostException, SocketException {
        SocketAddress me = new InetSocketAddress("192.168.35.219", 9756);
        SocketAddress drone = new InetSocketAddress("127.0.0.1", 9750);
        InetAddress addr = InetAddress.getByName("127.0.0.1");

        DatagramSocket serverSocket = new DatagramSocket(me);

        byte[] buf = new byte[256];

        DatagramPacket dronePacket = new DatagramPacket(buf, buf.length);

        Parser parser = new Parser();
        int cnt = 0;

        try {
            while (true){
                serverSocket.receive(dronePacket);

                byte[] data = dronePacket.getData();

                int length = dronePacket.getLength();

                MAVLinkMessage mavLinkMessage = MAVLinkUtils.getMessage(data, length)
                        .orElseThrow(() -> new IllegalArgumentException());

//                if(mavLinkMessage instanceof msg_monitoring)
//                System.out.println(mavLinkMessage);
                if(cnt % 100_000 == 0)
                    System.out.println(cnt);
                cnt++;

            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(cnt);
        }
    }
}
