package com.gcs.core.udp.domain.mavlink.service;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.supporter.error.exception.ApiException;
import com.gcs.supporter.mavlink.message.adapter.MoveableMonitoringAdapter;
import com.gcs.supporter.mavlink.util.MAVLinkUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.Message;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.util.Objects;

@Slf4j
@Service
public class MavlinkGatewayService {
    private final DatagramSocket socket;
    private final SimpMessagingTemplate template;
    private final AgentService agentService;

    public MavlinkGatewayService(SimpMessagingTemplate template, AgentService agentService) {
        this.template = template;
        this.agentService = agentService;
        try{
            socket = new DatagramSocket();
        } catch (SocketException e){
            throw new RuntimeException(e);
        }
    }

    public void publishToBrowser(Message<?> message){
        if(message.getPayload() instanceof byte[]) {
            byte[] payload = (byte[]) message.getPayload();
            MAVLinkMessage mavLinkMessage = MAVLinkUtils.getMessage(payload, payload.length)
                    .orElse(null);

//            log.info("{}", mavLinkMessage);

            if(mavLinkMessage instanceof msg_monitoring){
                msg_monitoring monitoring = (msg_monitoring) mavLinkMessage;
                try{
                    agentService.updateMove(monitoring.sysid, new MoveableMonitoringAdapter(monitoring));
                } catch (ApiException e){
                    log.debug("error", e);
                }
                template.convertAndSend("/topic/monitoring", monitoring);
            }
        }
    }

    public void sentToAgent(int sysid, byte[] message){
        InetAddress destAddress = agentService.getAgentInetAddress(sysid);
        Integer destPort = agentService.getAgentPort(sysid);

        send(new DatagramPacket(message, message.length, destAddress, destPort));
    }

    public void send(DatagramPacket packet){
        synchronized (socket){
            try {
                    socket.send(packet);
                    log.info("{}", packet);
            } catch (IOException e) {
                //TODO java.net.SocketException: Host is down handling
                log.error("error", e);
            }
        }
    }
}
