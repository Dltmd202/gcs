package com.gcs.monolith.socket.messagequeue.mavlink.serialization;

import com.MAVLink.Messages.MAVLinkMessage;
import com.gcs.supporter.util.mavlink.MAVLinkUtils;
import org.apache.kafka.common.serialization.Serializer;

public class MavlinkSerializer implements Serializer<MAVLinkMessage> {

    @Override
    public byte[] serialize(String topic, MAVLinkMessage data) {
        return MAVLinkUtils.getPacketData(data);
    }
}
