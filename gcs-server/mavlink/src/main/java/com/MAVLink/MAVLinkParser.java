package com.MAVLink;

import com.MAVLink.Messages.MAVLinkStats;

import java.util.Optional;

public class MAVLinkParser {

    enum MavParseState {
        MAVLINK_PARSE_STATE_UNINIT,
        MAVLINK_PARSE_STATE_IDLE,
        MAVLINK_PARSE_STATE_GOT_STX,
        MAVLINK_PARSE_STATE_GOT_LENGTH,
        MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS, // MAVLink 2
        MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS, // MAVLink 2
        MAVLINK_PARSE_STATE_GOT_SEQ,
        MAVLINK_PARSE_STATE_GOT_SYSID,
        MAVLINK_PARSE_STATE_GOT_COMPID,
        MAVLINK_PARSE_STATE_GOT_MSGID1,
        MAVLINK_PARSE_STATE_GOT_MSGID2, // MAVLink 2
        MAVLINK_PARSE_STATE_GOT_MSGID3, // MAVLink 2
        MAVLINK_PARSE_STATE_GOT_CRC1,
        MAVLINK_PARSE_STATE_GOT_CRC2, // MAVLink 2
        MAVLINK_PARSE_STATE_GOT_PAYLOAD,
        MAVLINK_PARSE_STATE_GOT_SIGNATURE, // MAVLink 2
    }

    public Optional<MAVLinkPacket> mavlink_parse_char(byte[] message) {
        MavParseState state = MavParseState.MAVLINK_PARSE_STATE_UNINIT;
        boolean isMavlink2 = false;
        MAVLinkPacket m = null;
        MAVLinkStats stats = new MAVLinkStats(false);


        for (byte c : message) {

            switch (state) {
                case MAVLINK_PARSE_STATE_UNINIT:
                case MAVLINK_PARSE_STATE_IDLE:

                    if (c == MAVLinkPacket.MAVLINK_STX_MAVLINK2) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_STX;
                        if (!isMavlink2) {
                            isMavlink2 = true;
                        }
                    } else if (c == MAVLinkPacket.MAVLINK_STX_MAVLINK1) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_STX;
                        if (isMavlink2) {
                            isMavlink2 = false;
                        }
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_STX:
                    m = new MAVLinkPacket(c, isMavlink2);
                    if (isMavlink2) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_LENGTH;
                    } else {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_LENGTH:
                    // MAVLink 1 and 2
                    m.incompatFlags = c;
                    if (c != 0 && c != 1) {
                        // message includes an incompatible feature flag
                        state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                        break;
                    }
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS;
                    break;

                case MAVLINK_PARSE_STATE_GOT_INCOMPAT_FLAGS:
                    // MAVLink 2 only
                    m.compatFlags = c;
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS;
                    break;

                case MAVLINK_PARSE_STATE_GOT_COMPAT_FLAGS:
                    m.seq = c;
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_SEQ;
                    break;

                case MAVLINK_PARSE_STATE_GOT_SEQ:
                    // back to MAVLink 1 and 2
                    m.sysid = c;
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_SYSID;
                    break;

                case MAVLINK_PARSE_STATE_GOT_SYSID:
                    // MAVLink 1 and 2
                    m.compid = c;
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_COMPID;
                    break;

                case MAVLINK_PARSE_STATE_GOT_COMPID:
                    // MAVLink 1 and 2
                    m.msgid = c;
                    if (isMavlink2) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_MSGID1;
                    } else if (m.len > 0) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_MSGID3;
                    } else {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID1:
                    // MAVLink 2 only
                    m.msgid |= c << 8;
                    state = MavParseState.MAVLINK_PARSE_STATE_GOT_MSGID2;
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID2:
                    // MAVLink 2 only
                    m.msgid |= c << 16;
                    if (m.len > 0) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_MSGID3;
                    } else {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_MSGID3:
                    // back to MAVLink 1 and 2
                    m.payload.add((byte) c);
                    if (m.payloadIsFilled()) {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_PAYLOAD;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_PAYLOAD:
                    boolean crcGen = m.generateCRC(m.payload.size());
                    // Check first checksum byte and verify the CRC was successfully generated (msg extra exists)
                    if (c != m.crc.getLSB() || !crcGen) {
                        state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                        stats.crcError();
                    } else {
                        state = MavParseState.MAVLINK_PARSE_STATE_GOT_CRC1;
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_CRC1:
                    // Check second checksum byte
                    if (c != m.crc.getMSB()) {
                        state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                        stats.crcError();
                    } else { // crc is good
                        stats.newPacket(m);

                        if (!isMavlink2 || (m.incompatFlags != 0x01)) {
                            // If no signature, then return the message.
                            state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                            return Optional.of(m);
                        } else {
                            // TODO: MAVLink 2 - signed
                            state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                            stats.crcError();
                        }
                    }
                    break;

                case MAVLINK_PARSE_STATE_GOT_CRC2:
                    // TODO: implement signature parsing and validation
                    state = MavParseState.MAVLINK_PARSE_STATE_IDLE;
                    stats.crcError();
                    break;
            }
        }


        return Optional.ofNullable(m);
    }

}
