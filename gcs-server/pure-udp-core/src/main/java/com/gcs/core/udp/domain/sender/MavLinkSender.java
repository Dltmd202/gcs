package com.gcs.core.udp.domain.sender;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.core.udp.domain.mavlink.service.MavlinkGatewayService;
import com.gcs.supporter.mavlink.util.MAVLinkUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class MavLinkSender {

    private final MavlinkGatewayService mavlinkService;

    @Pointcut("@annotation(com.gcs.supporter.mavlink.annotation.MavLinkOrder)")
    private void mavlinkOrder() { }

    public void send(MAVLinkMessage msg){
        mavlinkService.sentToAgent(msg.sysid, MAVLinkUtils.getMessage(msg));
    }

    @Around("mavlinkOrder()")
    public Object orderSendProxy(ProceedingJoinPoint joinPoint) throws Throwable {
        Object res = joinPoint.proceed();
        if(res instanceof Collection<?>){
            Collection<MAVLinkMessage> msg = (Collection<MAVLinkMessage>) res;
            msg.forEach(this::send);
        } else if(res instanceof MAVLinkMessage){
            MAVLinkMessage msg = (MAVLinkMessage) res;
            log.info("{}", msg);
            this.send(msg);
        }
        return res;
    }
}
