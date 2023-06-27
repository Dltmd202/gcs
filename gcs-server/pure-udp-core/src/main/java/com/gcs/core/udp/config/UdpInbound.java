package com.gcs.core.udp.config;

import com.gcs.core.udp.domain.mavlink.service.MavlinkGatewayService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

@Slf4j
public class UdpInbound {
    private static final int PORT = 9750;
    private static final int BUFFER_SIZE = 1024;
    private final DatagramSocket reciver;
    private final DatagramPacket recivePacket;
    private final MavlinkGatewayService mavlinkGatewayService;
    private final byte[] buffer;

    public UdpInbound(MavlinkGatewayService mavlinkGatewayService) {
        try{
            reciver = new DatagramSocket(PORT);
        } catch (SocketException e){
            throw new RuntimeException(e);
        }
        buffer = new byte[BUFFER_SIZE];
        recivePacket = new DatagramPacket(buffer, BUFFER_SIZE);
        this.mavlinkGatewayService = mavlinkGatewayService;
    }

    @PostConstruct
    public void init(){
        Thread thread = new Thread(() -> {
            while(true){
                try {
                    reciver.receive(recivePacket);
                    byte[] data = recivePacket.getData();

                    mavlinkGatewayService.publishToBrowser(data);
                    log.info("{}", data);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });
        thread.start();
    }


}
