package com.gcs.supporter.mavlink.message.parser;

import com.MAVLink.MAVLinkPacket;
import com.MAVLink.Messages.MAVLinkStats;

import static com.gcs.supporter.mavlink.message.parser.MavLinkParsingState.*;

public class MavLinkParser {

    private final MAVLinkStats stats;

    public MavLinkParser(MAVLinkStats stats){
        this.stats = stats;
    }

    public MAVLinkPacket parse(byte[] message){
        MavLinkParsingState state = MAVLINK_PARSE_STATE_UNINIT;
        MAVLinkPacket packet = null;
        boolean isMavlink2 = false;

        int length = message.length;

        for (int c = 0; c < length; c++){

            switch (state) {
                case MAVLINK_PARSE_STATE_UNINIT:
                case MAVLINK_PARSE_STATE_IDLE:
                    if (c == MAVLinkPacket.MAVLINK_STX_MAVLINK2) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_STX;
                        if (!isMavlink2) {
                            isMavlink2 = true;
                        }
                    } else if (c == MAVLinkPacket.MAVLINK_STX_MAVLINK1) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_STX;
                        if (isMavlink2) {
                            isMavlink2 = false;
                        }
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_STX:
                    packet = new MAVLinkPacket(c, isMavlink2);
                    if (isMavlink2) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_LENGTH;
                    } else {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_LENGTH:
                    packet.incompatFlags = c;
                    if (c != 0 && c != 1) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                        break;
                    }
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS;
                    break;

                case MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS:
                    // MAVLink 2 only
                    packet.compatFlags = c;
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
                    break;

                case MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS:
                    packet.seq = c;
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_SEQ;
                    break;

                case MAVLINK_PARSE_STATE_GOT_SEQ:
                    packet.sysid = c;
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_SYSID;
                    break;

                case MAVLINK_PARSE_STATE_GOT_SYSID:
                    // MAVLink 1 and 2
                    packet.compid = c;
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_COMPID;
                    break;

                case MAVLINK_PARSE_STATE_GOT_COMPID:
                    // MAVLink 1 and 2
                    packet.msgid = c;
                    if (isMavlink2) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_MSGID1;
                    } else if (packet.len > 0) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_MSGID3;
                    } else {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID1:
                    // MAVLink 2 only
                    packet.msgid |= c << 8;
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_MSGID2;
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID2:
                    // MAVLink 2 only
                    packet.msgid |= c << 16;
                    if (packet.len > 0) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_MSGID3;
                    } else {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID3:
                    // back to MAVLink 1 and 2
                    packet.payload.add((byte) c);
                    if (packet.payloadIsFilled()) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_PAYLOAD:
                    boolean crcGen = packet.generateCRC(packet.payload.size());
                    // Check first checksum byte and verify the CRC was successfully generated (msg extra exists)
                    if (c != packet.crc.getLSB() || !crcGen) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                        stats.crcError();
                    } else {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_GOT_CRC1;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_CRC1:
                    // Check second checksum byte
                    if (c != packet.crc.getMSB()) {
                        state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                        stats.crcError();
                    } else { // crc is good
                        stats.newPacket(packet);

                        if (!isMavlink2 || (packet.incompatFlags != 0x01)) {
                            // If no signature, then return the message.
                            state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                            return packet;
                        } else {
                            // TODO: MAVLink 2 - signed
                            state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                            stats.crcError();
                        }
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_CRC2:
                    // TODO: implement signature parsing and validation
                    state = MavLinkParsingState.MAVLINK_PARSE_STATE_IDLE;
                    stats.crcError();
                    break;
            } // switch
        }

        return packet;
    }
}
