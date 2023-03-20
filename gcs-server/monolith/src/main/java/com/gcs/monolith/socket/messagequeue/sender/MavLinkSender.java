package com.gcs.monolith.socket.messagequeue.sender;

import com.MAVLink.Messages.MAVLinkMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static com.gcs.monolith.constants.KafkaConfigurationConstants.SERVER_TO_AGENT_TOPIC;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class MavLinkSender {
    private final KafkaTemplate<Integer, MAVLinkMessage> kafkaTemplate;

    @Pointcut("@annotation(com.gcs.monolith.socket.messagequeue.sender.MavLinkOrder)")
    private void mavlinkOrder() { }

    public void send(MAVLinkMessage msg){
        kafkaTemplate.send(SERVER_TO_AGENT_TOPIC, msg.sysid, msg);
    }

    @Around("mavlinkOrder()")
    public Object orderSendProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = joinPoint.proceed();
        if(res instanceof Collection<?>){
            Collection<MAVLinkMessage> msg = (Collection<MAVLinkMessage>) res;
            msg.forEach(this::send);
        } else if(res instanceof MAVLinkMessage){
            MAVLinkMessage msg = (MAVLinkMessage) res;
            this.send(msg);
        }
        return res;
    }

}