package com.gcs.monolith.socket.messagequeue;

import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.swarm.msg_monitoring;
import com.gcs.domain.agent.service.AgentService;
import com.gcs.domain.mavlink.mask.MavLinkMonitoringFlag;
import com.gcs.domain.mavlink.message.MavLinkNotificationMessage;
import com.gcs.supporter.domain.mavlink.adapter.MoveableMonitoringAdapter;
import com.gcs.supporter.error.exception.ApiException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class CoreMessageConsumer {

    private final SimpMessagingTemplate template;
    private final AgentService agentService;

    @KafkaListener(
            topics = "agentToServer",
            groupId = "agentToServer",
            containerFactory = "mavlinkListenerContainerFactory"
    )
    public void publishToBrowser1(@Payload MAVLinkMessage message){
        if(message instanceof msg_monitoring) {
            msg_monitoring monitoring = (msg_monitoring) message;
            try{
                agentService.updateMove(monitoring.sysid, new MoveableMonitoringAdapter(monitoring));
            } catch (ApiException e){
                // TODO context 로드 되기 전까지 받은 메시지에 대한 정책 필요
                log.debug("error", e);
            }
            template.convertAndSend("/topic/monitoring", monitoring);
        }
    }

    private void checkNotification(msg_monitoring monitoring){
        for (MavLinkMonitoringFlag flag : MavLinkMonitoringFlag.values()) {
            if((monitoring.status1 & flag.getMask()) > 0){
                template.convertAndSend(
                        "/topic/notification",
                        new MavLinkNotificationMessage(monitoring.sysid, flag));
            }
        }
    }
}
