package com.gcs.supporter.mavlink.util;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkMessage;
import com.MAVLink.Parser;

import java.util.Optional;

/**
 * @author Dltmd202
 */
public class MAVLinkUtils {
    public static Optional<MAVLinkMessage> getMessage(byte[] data, int length){
        Parser parser = new Parser();
        MAVLinkPacket packet = null;
        MAVLinkMessage m;

        for (int i = 0; i < length; i++) {
            packet = parser.mavlink_parse_char(data[i]);

            if(packet != null){
                return Optional.of(packet.unpack());
            }
        }
        return Optional.empty();
    }

    public static byte[] getPacketData(MAVLinkMessage msg){
        return msg.pack().encodePacket();
    }

}
