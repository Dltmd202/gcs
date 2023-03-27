package com.gcs.core.mavlink.domain.service;

import com.gcs.domain.agent.service.AgentConfigureService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.*;

import static com.gcs.core.mavlink.config.CoreConstants.*;

@Slf4j
@Service
public class CoreMessageService {

    private final KafkaTemplate<String, byte[]> kafkaTemplate;
    private final AgentConfigureService agentService;
    private DatagramSocket socket;

    public CoreMessageService(KafkaTemplate<String, byte[]> kafkaTemplate, AgentConfigureService agentService) {
        this.kafkaTemplate = kafkaTemplate;
        this.agentService = agentService;
        try {
            socket = new DatagramSocket();
        } catch (SocketException e) {
            throw new RuntimeException(e);
        }
    }

    public byte[] publishToServer(Message<?> message){
        byte[] payload = (byte[]) message.getPayload();
        kafkaTemplate.send(AGENT_TO_SERVER_TOPIC, payload);
        return payload;
    }

    public void sendToAgent(Message<byte[]> message){
        Integer key =  message.getHeaders().get(KafkaHeaders.RECEIVED_MESSAGE_KEY, Integer.class);
        byte[] payload = message.getPayload();

        // TODO exception handling
        InetAddress destinationAddress = agentService.getAgentInetAddress(key);
        Integer port = agentService.getAgentPort(key);

        send(new DatagramPacket(payload, payload.length, destinationAddress, port));
    }

    public void send(DatagramPacket packet){
        try {
            socket.send(packet);
        } catch (IOException e) {
            //TODO java.net.SocketException: Host is down handling
            log.error("error", e);
        }
    }
}
