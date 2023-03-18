package com.gcs.monolith.socket.messagequeue.mavlink.serialization;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;
import org.apache.kafka.common.serialization.Deserializer;

import java.util.Arrays;

public class MavlinkDeserializer implements Deserializer<MAVLinkMessage> {

    @Override
    public MAVLinkMessage deserialize(String topic, byte[] data) {
        if(data == null) return null;
        Parser parser = new Parser();
        MAVLinkPacket packet = null;
        MAVLinkMessage m;

        for (int i = 0; i < data.length; i++) {
            packet = parser.mavlink_parse_char(data[i]);

            if(packet != null){
                return packet.unpack();
            }
        }
        return null;
    }
}
